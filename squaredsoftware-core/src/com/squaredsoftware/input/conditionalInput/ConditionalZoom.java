package com.squaredsoftware.input.conditionalInput;

import com.squaredsoftware.input.vgesture.ZoomListener;

public class ConditionalZoom implements ZoomListener {
	private Conditional conditional;
	private ZoomListener listener;
	
	public ConditionalZoom(Conditional conditional, ZoomListener listener) {
		this.conditional = conditional;
		this.listener = listener;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		if(conditional.isConditionMet()) {
			listener.zoom(initialDistance, distance);
		}
		return false;
	}
}
