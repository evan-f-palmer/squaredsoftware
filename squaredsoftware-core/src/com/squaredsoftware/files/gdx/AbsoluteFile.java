package com.squaredsoftware.files.gdx;

import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.squaredsoftware.files.File;

public class AbsoluteFile implements File {

	@Override
	public boolean exists(String filename) {
		return Gdx.files.absolute(filename).exists();
	}

	@Override
	public InputStream read(String filename) {
		return Gdx.files.absolute(filename).read();
	}
}
