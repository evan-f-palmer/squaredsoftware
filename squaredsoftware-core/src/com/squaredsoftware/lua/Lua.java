package com.squaredsoftware.lua;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;

import com.badlogic.gdx.Gdx;
import com.squaredsoftware.lua.gdx.GdxLuaGlobals;

public class Lua {
	private static final int SQUARED_SOFTWARE_VERSION_INDEX = 0;
	private static final String[] SQUARED_SOFTWARE_VERSIONS = {
		"1.0.4"
	};
	
	private static String SQUARED_SOFTWARE_GIT_ID = "";
	
	private static final String SQUARED_SOFTWARE_VERSION = SQUARED_SOFTWARE_VERSIONS[SQUARED_SOFTWARE_VERSION_INDEX];
	private static final String LUAJ_VERSION = org.luaj.vm2.Lua._VERSION + " Copyright (c) 2012 Luaj.org.org";
	private static String VERSION = "";
	private static boolean isGitIdLoaded = false;
	private Globals globals;
	
	public Lua() {
		globals = new GdxLuaGlobals();
	}
	
	public class LuaLoadFileException extends RuntimeException {private static final long serialVersionUID = -8801481448599999342L;}
	public class LuaRunStringException extends RuntimeException{private static final long serialVersionUID = 7228545114872663129L; }
	
	public void runString(String string) {
		try {
			processScript(new ByteArrayInputStream(string.getBytes()), "=runString", null, 0 );
		} catch (Exception e) {
			LuaRunStringException luaRunStringException = new LuaRunStringException();
			luaRunStringException.setStackTrace(e.getStackTrace());
			throw luaRunStringException;
		}
	}
	
	public void loadFile(String fileName) {
		try {
			processScript( globals.finder.findResource(fileName), fileName, null, 0);
		} catch (IOException e) {
			LuaLoadFileException luaLoadFileException = new LuaLoadFileException();
			luaLoadFileException.setStackTrace(e.getStackTrace());
			throw luaLoadFileException;
		}
	}

	public String getVersion() {
		if(!isGitIdLoaded) {
			isGitIdLoaded = true;
			loadVersion();
		}
		return VERSION;
	}
	
	public void main( String[] args ) throws IOException {
		globals = new GdxLuaGlobals();
		
		if(args.length == 0) {
			interactiveMode();
		} else {
			processArgsAndExecute(args);
		}
	}

	private void loadVersion() {
		loadFile("SquaredSoftwareGitId.lua");
		String ver = (String) CoerceLuaToJava.coerce(globals.get("SquaredSoftwareGitId"), String.class);
		SQUARED_SOFTWARE_GIT_ID = ver.trim();
		
		VERSION = "LuaJ Version            : " + LUAJ_VERSION + "\n"
			  	+ "Squared Software Version: " + SQUARED_SOFTWARE_VERSION + "\n"
				+ "Squared Software Git ID : " +  SQUARED_SOFTWARE_GIT_ID;
	}

	private void processArgsAndExecute(String[] args) {
		int index = 0;
		if(args[0].contains("-v")) {
			System.out.println(getVersion());
			index = 1;
		} 
		
		try {
			for (;index<args.length; index++ ) {
				processScript( globals.finder.findResource(args[index]), args[index], args, index);
			}
		} catch ( IOException ioe ) {
			Gdx.app.error("Lua ERROR", ioe.toString() );
			Gdx.app.exit();
		}
	}

	private void interactiveMode() throws IOException {
		BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
		System.out.println(getVersion());
		while ( true ) {
			System.out.print("> ");
			System.out.flush();
			String line = reader.readLine();
			if ( line == null ) { 
				break;
			} else if(line.length() > 0) {
				processScript( new ByteArrayInputStream(line.getBytes()), "=stdin", null, 0 );
			}
		}
	}
	
	private void processScript( InputStream script, String chunkname, String[] args, int firstarg ) throws IOException {
		try {
			LuaValue c;
			try {
				script = new BufferedInputStream(script);
				c = globals.load(script, chunkname, "bt", globals);
			} finally {
				script.close();
			}
			Varargs scriptargs = setGlobalArg(chunkname, args, firstarg, globals);
			c.invoke( scriptargs );
		} catch (LuaError e) {
			Gdx.app.error("Lua ERROR", e.getMessage());
		} catch ( Exception e ) {
			Gdx.app.error("Lua ERROR", e.getMessage());
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
}