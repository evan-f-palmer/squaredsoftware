package com.squaredsoftware.input.conditionalInput;

import com.squaredsoftware.input.vgesture.TouchDownListener;

public class ConditionalTouchDown implements TouchDownListener{
	Conditional conditional;
	TouchDownListener listener;
	
	public ConditionalTouchDown(Conditional conditional, TouchDownListener listener) {
		this.conditional = conditional;
		this.listener = listener;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if(conditional.isConditionMet()) {
			listener.touchDown(x, y, pointer, button);
		}
		return false;
	}
}
