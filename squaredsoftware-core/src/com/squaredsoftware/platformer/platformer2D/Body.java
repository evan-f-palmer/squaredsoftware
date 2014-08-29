package com.squaredsoftware.platformer.platformer2D;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.squaredsoftware.shaders.BackgroundShader;

public class Body {	
	private Bounding bounding;
	private Vector2 veloctiy;
	
	public Body(Bounding xBounding) {
		bounding = xBounding;
		veloctiy = new Vector2(0, 0);
	}

	public void update() {
		bounding.translate(veloctiy.x, veloctiy.y);
	}
	
	public void render(ShapeRenderer shapeRenderer) {
		bounding.render(shapeRenderer);
	}
	
	public void render(BackgroundShader shader) {
		shader.render(bounding.bounds.x, bounding.bounds.y, bounding.bounds.width, bounding.bounds.height);
	}
	
	public void resolveIntersection(Bounding xBounding) {
		if(isIntersecting(xBounding))
			separateFromBounding(xBounding);
	}
	
	private void separateFromBounding(Bounding xBounding) {
		setYVelocity(0);
		bounding.setY(xBounding.bounds.y + xBounding.bounds.height);
	}
	
	public boolean isIntersecting(Bounding xBounding) {
		return bounding.isIntersecting(xBounding);
	}
	
	public void addVelocity(float x, float y) {
		veloctiy.add(x, y);
	}
	
	public void setVelocity(float x, float y) {
		veloctiy.set(x, y);
	}
	
	public void setXVelocity(float x) {
		veloctiy.x = x;
	}
	
	public void setYVelocity(float y) {
		veloctiy.y = y;
	}
}