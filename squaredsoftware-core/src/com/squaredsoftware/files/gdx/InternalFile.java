package com.squaredsoftware.files.gdx;

import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.squaredsoftware.files.File;

public class InternalFile implements File {

	@Override
	public boolean exists(String filename) {
		return Gdx.files.internal(filename).exists();
	}

	@Override
	public InputStream read(String filename) {
		return Gdx.files.internal(filename).read();
	}
}
