package com.squaredsoftware.platformer;


public class Platformer {
	private Jump    jump;
	private Gravity gravity;
	
	public Platformer(Jump xJump, Gravity xGravity) {
		jump    = xJump;
		gravity = xGravity;
	}

	public void update() {
		gravity.apply();
	}
	
	public void jump() {
		jump.apply();
	}	
}