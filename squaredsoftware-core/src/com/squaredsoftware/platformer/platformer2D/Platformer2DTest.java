package com.squaredsoftware.platformer.platformer2D;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.squaredsoftware.input.VirtualInput;
import com.squaredsoftware.input.button.VirtualButtonPressedListener;
import com.squaredsoftware.input.button.VirtualButtonReleasedListener;
import com.squaredsoftware.input.gdx.InputDriver;
import com.squaredsoftware.shaders.BackgroundShader;

public class Platformer2DTest implements ApplicationListener{

	Player player;
	
	ShapeRenderer shapeRenderer;
	OrthographicCamera cam;
	
	World world;
	
	float width;
	float height;
	
	VirtualInput vInput;
	
	BackgroundShader background;
	BackgroundShader playerShader;
	
	@Override
	public void create() {
		width  = World.WIDTH;
		height = World.HEIGHT;
		
		background = BackgroundShader.createBackgroundShader("cubeWorld.fsh");
		playerShader = BackgroundShader.createBackgroundShader("passthrough.fsh");
		
		cam = new OrthographicCamera(width, height);
		cam.translate(width/2, height/2);
		cam.update();
		
		shapeRenderer = new ShapeRenderer();
		
		InputDriver inputDriver = new InputDriver();
		vInput = new VirtualInput();
		vInput.initDriver(inputDriver);
		
		vInput.mapVKey("jump",  Keys.SPACE);
		vInput.mapVKey("left",  Keys.LEFT);
		vInput.mapVKey("right", Keys.RIGHT);
		vInput.addKeyPressed("jump", new VirtualButtonPressedListener() {
			@Override
			public void keyPressed() {
				player.jump();
			}
		});
		
		Gdx.input.setInputProcessor(inputDriver.getInputProcessor());
		
		world = new World();
		player = new Player(world);
	}


	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		background.render(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		player.update();
		world.handleCollisions(player.getBody());
		
		playerShader.setProjectionMatrix(cam.combined);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.setProjectionMatrix(cam.combined);
		shapeRenderer.begin(ShapeType.Line);
		
		world.render(shapeRenderer);
		player.render(shapeRenderer);
		player.render(playerShader);
		
		shapeRenderer.end();
		
		updateInput();
	}
	
	public void updateInput() {
		if(!vInput.isKeyPressed("right") && !vInput.isKeyPressed("left")) {
			player.stopHorizontalMovement();
		} else {
			if(vInput.isKeyPressed("left")){
				player.moveLeft();
			}
			if(vInput.isKeyPressed("right")){
				player.moveRight();
			}
		}
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

	@Override
	public void dispose() {
		shapeRenderer.dispose();
		background.dispose();
		playerShader.dispose();
	}
}
