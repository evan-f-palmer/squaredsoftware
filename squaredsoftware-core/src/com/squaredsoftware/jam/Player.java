package com.squaredsoftware.jam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.squaredsoftware.input.VirtualInput;
import com.squaredsoftware.input.gdx.InputDriver;

public class Player {
	final public static float SCORE_MULTIPLIER = 5;
	public static Circle  body            = new Circle(0,0,7);
	public static double  time            = 0;
	public static long    score           = 0;
	public static int     collectibles    = 0;
	public static float   movementSpeed   = 7;
	public static Vector2 velocity        = new Vector2();
	
	public static void update() {
		updateMovement();
		wrapIfOutOfBounds();
		updateTime();
		updateScore();
	}
	
	private static void updateMovement(){
		velocity.clamp(-movementSpeed, movementSpeed);
		body.x += velocity.x;
		body.y += velocity.y;
	}
	
	private static void updateTime() {
		time += Gdx.graphics.getDeltaTime();
	}
	
	private static void updateScore() {
		score = (long) (time + (SCORE_MULTIPLIER * collectibles) ) * 10;
	}
	
	private static void wrapIfOutOfBounds() {
		if(body.x + body.radius < -Main.width>>1) {
			body.x = (Main.width>>1) + body.radius;
		} else if (body.x - body.radius >  Main.width>>1) {
			body.x = (-Main.width>>1) - body.radius;
		}
		
		if(body.y + body.radius < -Main.height>>1) {
			body.y = (Main.height>>1) + body.radius;
		} else if (body.y - body.radius >  Main.height>>1) {
			body.y = (-Main.height>>1) - body.radius;
		}
	}
}
