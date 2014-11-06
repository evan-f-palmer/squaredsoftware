package com.squaredsoftware.lua.gdx;

import java.io.InputStream;

import org.luaj.vm2.lib.ResourceFinder;

import com.squaredsoftware.files.*;
import com.squaredsoftware.files.gdx.*;

public class GdxLuaFileFinder implements ResourceFinder {
	private File filePriorities[] = {new InternalFile(), 
									 new LocalFile(), 
									 new ExternalFile(), 
									 new AbsoluteFile()};
	
	@Override
	public InputStream findResource(String filename) {
		for (File file : filePriorities) {
			if(file.exists(filename)) {
				return file.read(filename);
			}
		}
		return null;
	}	
}
