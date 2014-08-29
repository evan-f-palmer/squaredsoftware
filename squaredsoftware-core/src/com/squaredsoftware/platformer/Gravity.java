package com.squaredsoftware.platformer;

public abstract class Gravity {	
	public final void apply() {
		if(shouldApply()) 	
			performGravity();
	}
	
	public abstract boolean shouldApply();
	protected abstract void performGravity();
}
