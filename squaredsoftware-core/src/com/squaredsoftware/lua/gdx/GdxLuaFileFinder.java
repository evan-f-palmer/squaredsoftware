package com.squaredsoftware.lua.gdx;

import java.io.InputStream;

import org.luaj.vm2.lib.ResourceFinder;

import com.badlogic.gdx.Gdx;

public class GdxLuaFileFinder implements ResourceFinder{
	@Override
	public InputStream findResource(String filename) {
		if(Gdx.files.internal(filename).exists()) {
			return Gdx.files.internal(filename).read();
		}
		
		if(Gdx.files.local(filename).exists()) {
			return Gdx.files.local(filename).read();
		}
		
		if(Gdx.files.external(filename).exists()) {
			return Gdx.files.external(filename).read();
		}
		
		if(Gdx.files.absolute(filename).exists()) {
			return Gdx.files.absolute(filename).read();
		}
		
		return null;
	}	
}
