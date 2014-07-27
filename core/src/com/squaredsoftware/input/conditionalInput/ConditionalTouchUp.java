package com.squaredsoftware.input.conditionalInput;

import com.squaredsoftware.input.vgesture.TouchUpListener;

public class ConditionalTouchUp implements TouchUpListener{
	Conditional condition;
	TouchUpListener listener;
	
	public ConditionalTouchUp(Conditional condition, TouchUpListener listener) {
		this.condition = condition;
		this.listener = listener;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if(condition.isConditionMet()) {
			listener.touchUp(x, y, pointer, button);
		}
		return false;
	}
}
