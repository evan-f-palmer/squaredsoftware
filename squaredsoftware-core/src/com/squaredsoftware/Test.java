package com.squaredsoftware;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.squaredsoftware.lua.Lua;

public class Test implements ApplicationListener {
	SpriteBatch batch;
	Texture img;
	
	Lua lua ;
	
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		lua = new Lua();
		lua.runString("print(\'hello\')");
		lua.loadFile("hello.lua");
		
	}

	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	public void dispose() {
		batch.dispose();
		img.dispose();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
