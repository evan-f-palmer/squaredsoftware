package com.squaredsoftware.lua;

/*******************************************************************************
* Copyright (c) 2009-2012 Luaj.org. All rights reserved.
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
******************************************************************************/

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Print;
import org.luaj.vm2.Varargs;

import com.badlogic.gdx.Gdx;
import com.squaredsoftware.lua.gdx.GdxLuaGlobals;

/**
 * lua command for use in JSE environments.
 */
public class Lua {
	private static final String[] squaredSoftwareVersions = {
		"1.0.0"
	};
	private static final int squaredSoftwareVersionIndex = 0;
	private static final String squaredSoftwareVersion = squaredSoftwareVersions[squaredSoftwareVersionIndex];
	
	private static final String version = org.luaj.vm2.Lua._VERSION + " Copyright (c) 2012 Luaj.org.org\n"
										+ "Squared Software Version: "+ squaredSoftwareVersion;

	private static final String usage = 
		"usage: java -cp luaj-jse.jar lua [options] [script [args]].\n" +
		"Available options are:\n" +
		"  -e stat  execute string 'stat'\n" +
		"  -l name  require library 'name'\n" +
		"  -i       enter interactive mode after executing 'script'\n" +
		"  -v       show version information\n" +
		"  -b      	use luajc bytecode-to-bytecode compiler (requires bcel on class path)\n" +
		"  -n      	nodebug - do not load debug library by default\n" +
		"  -p      	print the prototype\n" +
		"  -c enc  	use the supplied encoding 'enc' for input files\n" +
		"  --       stop handling options\n" +
		"  -        execute stdin and stop handling options";

	private static void usageExit() {
		System.out.println(usage);
		System.exit(-1);		
	}

	private Globals globals;
	private static boolean print = false;
	private static String encoding = null;
	
	public Lua() {
		globals = new GdxLuaGlobals();
	}
	
	public void main( String[] args ) throws IOException {

		// process args
		boolean interactive = (args.length == 0);
		boolean versioninfo = false;
		boolean processing = true;
		try {
			// stateful argument processing
			for ( int i=0; i<args.length; i++ ) {
				if ( ! processing || ! args[i].startsWith("-") ) {
					// input file - defer to last stage
					break;
				} else if ( args[i].length() <= 1 ) {
					// input file - defer to last stage
					break;
				} else {
					switch ( args[i].charAt(1) ) {
					case 'e':
						if ( ++i >= args.length )
							usageExit();
						// input script - defer to last stage
						break;
					case 'b':
						break;
					case 'l':
						if ( ++i >= args.length )
							usageExit();
						break;
					case 'i':
						interactive = true;
						break;
					case 'v':
						versioninfo = true;
						break;
					case 'n':
						break;
					case 'p':
						print = true;
						break;
					case 'c':
						if ( ++i >= args.length )
							usageExit();
						encoding = args[i];
						break;
					case '-':
						if ( args[i].length() > 2 )
							usageExit();
						processing = false;
						break;
					default:
						usageExit();
						break;
					}
				}
			}

			// echo version
			if ( versioninfo )
				System.out.println(version);
			
			// new lua state
			globals = new GdxLuaGlobals();
			
			// input script processing
			processing = true;
			for ( int i=0; i<args.length; i++ ) {
				if ( ! processing || ! args[i].startsWith("-") ) {
					processScript( globals.finder.findResource(args[i]), args[i], args, i );
					break;
				} else if ( "-".equals( args[i] ) ) {
					processScript( System.in, "=stdin", args, i );
					break;
				} else {
					switch ( args[i].charAt(1) ) {
					case 'l':
					case 'c':
						++i;
						break;
					case 'e':
						++i;
						processScript( new ByteArrayInputStream(args[i].getBytes()), "string", args, i );
						break;
					case '-':
						processing = false;
						break;
					}
				}
			}
			
			if ( interactive )
				interactiveMode();
			
		} catch ( IOException ioe ) {
			System.err.println( ioe.toString() );
			System.exit(-2);
		}
	}

	public void runString(String string) throws IOException {
		processScript(new ByteArrayInputStream(string.getBytes()), "=runString", null, 0 );
	}
	
	public void loadFile(String fileName) throws IOException {
		processScript( globals.finder.findResource(fileName), fileName, null, 0);
	}
	
	private void processScript( InputStream script, String chunkname, String[] args, int firstarg ) throws IOException {
		try {
			LuaValue c;
			try {
				script = new BufferedInputStream(script);
				c = encoding != null? 
						globals.load(new InputStreamReader(script, encoding), chunkname):
						globals.load(script, chunkname, "bt", globals);
			} finally {
				script.close();
			}
			if (print && c.isclosure())
				Print.print(c.checkclosure().p);
			Varargs scriptargs = setGlobalArg(chunkname, args, firstarg, globals);
			c.invoke( scriptargs );
		} catch (LuaError e) {
			Gdx.app.error("Lua ", e.getMessage());
		} catch ( Exception e ) {
			e.printStackTrace( System.err );
		}
	}

	private Varargs setGlobalArg(String chunkname, String[] args, int i, LuaValue globals) {
		if (args == null)
			return LuaValue.NONE;
		LuaTable arg = LuaValue.tableOf();
		for ( int j=0; j<args.length; j++ )
			arg.set( j-i, LuaValue.valueOf(args[j]) );
		arg.set(0, LuaValue.valueOf(chunkname));
		arg.set(-1, LuaValue.valueOf("luaj"));
		globals.set("arg", arg);
		return arg.unpack();
	}

	private void interactiveMode() throws IOException {
		BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
		System.out.println(version);
		while ( true ) {
			System.out.print("> ");
			System.out.flush();
			String line = reader.readLine();
			if ( line == null )
				return;
			if(line.length() > 0)
				processScript( new ByteArrayInputStream(line.getBytes()), "=stdin", null, 0 );
		}
	}
}