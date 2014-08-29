package com.squaredsoftware.platformer.platformer2D;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.squaredsoftware.platformer.Gravity;
import com.squaredsoftware.platformer.Jump;
import com.squaredsoftware.platformer.Platformer;
import com.squaredsoftware.shaders.BackgroundShader;

public class Player {
	private Body body = new Body(new Bounding(new Rectangle(100, 100, 20, 20)));
	private World world;
	
	public Player(World xWorld) {
		world = xWorld;
	}
	
	Jump platformerJump2D = new Jump() {
		@Override
		protected void performJump() {
			body.setYVelocity(10);
		}
		
		@Override
		public boolean canJump() {
			return world.isGrounded(body);
		}
	};
	
	Gravity platformerGravity2D = new Gravity() {
		@Override
		public boolean shouldApply() {
			return !world.isGrounded(body);
		}
		
		@Override
		protected void performGravity() {
			body.addVelocity(0, -1.001f);
		}
	};
	
	Platformer platformer2D = new Platformer(platformerJump2D, platformerGravity2D);
	
	public void update() {
		body.update();
		platformer2D.update();
	}
	
	public void jump() {
		platformer2D.jump();
	}
	
	public Body getBody() {
		return body;
	}
	
	public void moveLeft() {
		body.setXVelocity(-4);
	}
	
	public void moveRight() {
		body.setXVelocity(4);
	}
	
	public void stopHorizontalMovement() {
		body.setXVelocity(0);
	}
	
	public void render(ShapeRenderer shapeRenderer) {
		body.render(shapeRenderer);
	}

	public void render(BackgroundShader playerShader) {
		body.render(playerShader);
	}
}
