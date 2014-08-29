package com.squaredsoftware.platformer.platformer2D;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class World {
	public static final float WIDTH  = 600;
	public static final float HEIGHT = 600;

	private Bounding[] floors = { 
			                      new Bounding(new Rectangle(0, 80,  WIDTH, 10)),
			                      new Bounding(new Rectangle(20, 120, WIDTH, 10)),
			                      new Bounding(new Rectangle(20, 160, WIDTH, 10)),
			                      new Bounding(new Rectangle(20, 200, WIDTH, 10)),
			                      new Bounding(new Rectangle(20, 240, WIDTH, 10)),
			                      new Bounding(new Rectangle(20, 280, WIDTH, 10)),
			                      new Bounding(new Rectangle(20, 320, WIDTH, 10)),
			                      new Bounding(new Rectangle(20, 360, WIDTH, 10)),
			                      new Bounding(new Rectangle(20, 400, WIDTH, 10))
			                    };
	public World() {
		
	}
	
	public void handleCollisions(Body xBody) {
		for (Bounding ground : floors)
		    if(xBody.isIntersecting(ground)) 
			    xBody.resolveIntersection(ground);
	}
	
	public void render(ShapeRenderer shapeRenderer) {
		for (Bounding ground : floors)
		    ground.render(shapeRenderer);
	}
	
	public boolean isGrounded(Body xBody) {
		for (Bounding ground : floors)
		    if(xBody.isIntersecting(ground)) 
			    return true;
		return false;
	}
}
