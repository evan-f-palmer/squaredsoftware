package com.squaredsoftware.jam;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.squaredsoftware.input.VirtualInput;
import com.squaredsoftware.input.button.VirtualButtonPressedListener;
import com.squaredsoftware.input.gdx.InputDriver;
import com.squaredsoftware.shaders.BackgroundShader;
import com.sun.java.swing.plaf.gtk.GTKConstants.ShadowType;

public class Main implements ApplicationListener {
	SpriteBatch batch;
	public static ApplicationListener currentState;
	BackgroundShader background;
	ShapeRenderer shapeRenderer;
	OrthographicCamera cam;
	public InputDriver inputDriver;
	public VirtualInput vInput;
	
	public static int width = 800;
	public static int height = 800;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = BackgroundShader.createBackgroundShader("color.fsh");
		cam = new OrthographicCamera(width, height);
		shapeRenderer = new ShapeRenderer();
		setupPlayerInput();
	}

	@Override
	public void render () {
		update();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		background.render(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		cam.update();
		shapeRenderer.setProjectionMatrix(cam.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.circle(Player.body.x, Player.body.y, Player.body.radius);
		shapeRenderer.end();
	}
	
	public void update() {
		Player.update();
		updateInput();
	}
	
	public void updateInput() {
		Player.velocity.x = 0;
		Player.velocity.y = 0;
		
		if(!vInput.isKeyPressed("up") || !vInput.isKeyPressed("down")) {
			if(vInput.isKeyPressed("up")) {
				Player.velocity.y += Player.movementSpeed;
			} else if(vInput.isKeyPressed("down")) {
				Player.velocity.y -= Player.movementSpeed;
			}
		}
		if(!vInput.isKeyPressed("left") || !vInput.isKeyPressed("right")) {
			if(vInput.isKeyPressed("left")) {
				Player.velocity.x -= Player.movementSpeed;
			} else if (vInput.isKeyPressed("right")){
				Player.velocity.x += Player.movementSpeed;
			}
		}
	}
	
	public void setupPlayerInput() {
		inputDriver = new InputDriver();
		vInput = new VirtualInput();
		vInput.initDriver(inputDriver);	
		
		vInput.mapVKey("up", Keys.UP);
		vInput.mapVKey("up", Keys.W);
		vInput.mapVKey("down", Keys.DOWN);
		vInput.mapVKey("down", Keys.S);
		vInput.mapVKey("left", Keys.LEFT);
		vInput.mapVKey("left", Keys.A);
		vInput.mapVKey("right", Keys.RIGHT);
		vInput.mapVKey("right", Keys.D);
		vInput.mapVKey("warp", Keys.SPACE);

		
		vInput.addKeyPressed("warp", new VirtualButtonPressedListener() {
			@Override
			public void keyPressed() {
				System.out.println("warp");
			}
		});
		
		Gdx.input.setInputProcessor(inputDriver.getInputProcessor());
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
		batch.dispose();
		background.dispose();
	}
}
