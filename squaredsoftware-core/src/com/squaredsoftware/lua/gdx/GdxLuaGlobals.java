package com.squaredsoftware.lua.gdx;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.Bit32Lib;
import org.luaj.vm2.lib.CoroutineLib;
import org.luaj.vm2.lib.PackageLib;
import org.luaj.vm2.lib.StringLib;
import org.luaj.vm2.lib.TableLib;
import org.luaj.vm2.lib.jse.JseBaseLib;
import org.luaj.vm2.lib.jse.JseIoLib;
import org.luaj.vm2.lib.jse.JseMathLib;
import com.squaredsoftware.lua.LuaJavaLib;
import com.squaredsoftware.lua.LuaOSLib;

public class GdxLuaGlobals extends Globals {
    public GdxLuaGlobals() {
    	loadStandardLibs();
		loadCompiler();
		loadFileFinder();
		LoadState.install(this);
    }
    
    private void loadFileFinder() {
		finder = new GdxLuaFileFinder();
	}

	private void loadCompiler() {
		LuaC.install(this);
		compiler = LuaC.instance;
	}

	private void loadStandardLibs() {
		load(new JseBaseLib());
		load(new PackageLib());
		load(new Bit32Lib());
		load(new TableLib());
		load(new StringLib());
		load(new CoroutineLib());
		load(new JseMathLib());
		load(new JseIoLib());
		load(new LuaOSLib());
		load(new LuaJavaLib());
	}
}
