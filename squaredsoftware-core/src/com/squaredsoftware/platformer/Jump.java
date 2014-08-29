package com.squaredsoftware.platformer;

public abstract class Jump {
	public final void apply() {
		if(canJump()) 
			performJump();
	}
	
	public abstract boolean canJump();
	protected abstract void performJump();
}
