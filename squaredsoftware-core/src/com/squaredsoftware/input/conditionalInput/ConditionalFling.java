package com.squaredsoftware.input.conditionalInput;

import com.squaredsoftware.input.vgesture.FlingListener;

public class ConditionalFling implements FlingListener{
	private Conditional conditional;
	private FlingListener listener;
	
	public ConditionalFling(Conditional conditional, FlingListener listener) {
		this.conditional = conditional;
		this.listener = listener;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if(conditional.isConditionMet()) {
			listener.fling(velocityX, velocityY, button);
		}
		return false;
	}
}
