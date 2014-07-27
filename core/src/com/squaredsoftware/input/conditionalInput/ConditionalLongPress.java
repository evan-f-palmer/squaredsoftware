package com.squaredsoftware.input.conditionalInput;

import com.squaredsoftware.input.vgesture.LongPressListener;

public class ConditionalLongPress implements LongPressListener{
	Conditional conditional;
	LongPressListener listner;
	
	public ConditionalLongPress(Conditional conditional, LongPressListener listener) {
		this.conditional = conditional;
		this.listner = listener;
	}

	@Override
	public boolean longPress(float x, float y) {
		if(conditional.isConditionMet()) {
			listner.longPress(x, y);
		}
		return false;
	}

}
