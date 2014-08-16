package com.squaredsoftware.desktop.luaInterpreter;

import java.io.IOException;

import com.squaredsoftware.desktop.GdxMinimalistDesktopLauncher;
import com.squaredsoftware.lua.Lua;

public class LuaInterpreter {
	public static void main (String[] arg) {		
		GdxMinimalistDesktopLauncher.load();
		Lua lua = new Lua();
		try {
			lua.main(arg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}