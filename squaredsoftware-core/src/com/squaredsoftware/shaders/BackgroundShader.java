package com.squaredsoftware.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

public class BackgroundShader {
	
	public static BackgroundShader createBackgroundShader(String fragShaderFile) {
		return new BackgroundShader(fragShaderFile, "default.vsh");
	}
	
	public static BackgroundShader createBackgroundShader(String fragShaderFile, String vertexShaderFile) {
		return new BackgroundShader(fragShaderFile, vertexShaderFile);
	}
	
	public ShaderProgram shader;
	private SpriteBatch batch;
	private Texture blankTexture;
	private float time;
	
	private BackgroundShader(String fragShaderFile, String vertexShaderFile) {
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal(vertexShaderFile), Gdx.files.internal(fragShaderFile));
		if(!shader.isCompiled()) {
			throw new RuntimeException("Shader " + fragShaderFile + " failed to compiled");
		}
		batch = new SpriteBatch();
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		blankTexture = new Texture(pixmap);
		pixmap.dispose();
		time = 0;
		
		batch.setShader(shader);
	}
	
	public void setProjectionMatrix(Matrix4 projection) {
		batch.setProjectionMatrix(projection);
	}
	
	public void render(float x, float y, float width, float height) {
		time += Gdx.graphics.getDeltaTime();
		
		shader.begin();
		shader.setUniformf("time", time);
		shader.setUniformf("resolution", width, height);
		shader.end();
		
		batch.begin();
		batch.draw(blankTexture, x, y, width, height);
		batch.end();
	}
	
	public void dispose() {
		shader.dispose();
		blankTexture.dispose();
		batch.dispose();
	}
}
