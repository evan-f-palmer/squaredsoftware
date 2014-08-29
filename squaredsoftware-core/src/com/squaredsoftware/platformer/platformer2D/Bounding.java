package com.squaredsoftware.platformer.platformer2D;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Bounding {
	Rectangle bounds;
	
	public Bounding(Rectangle xBounds) {
		bounds = xBounds;
	}
	
	public boolean isIntersecting(Bounding otherBounding) {
		
		return bounds.x <= otherBounding.bounds.x + otherBounding.bounds.width 
		    && bounds.x + bounds.width >= otherBounding.bounds.x 
		    && bounds.y <= otherBounding.bounds.y + otherBounding.bounds.height 
		    && bounds.y + bounds.height >= otherBounding.bounds.y;
	}
	
	public void render(ShapeRenderer shapeRenderer){
		shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	public void translate(float x, float y) {
		bounds.x += x;
		bounds.y += y;
	}
	
	public void setY(float y) {
		bounds.y = y;
	}
}
