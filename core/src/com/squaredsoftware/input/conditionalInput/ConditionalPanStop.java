package com.squaredsoftware.input.conditionalInput;

import com.squaredsoftware.input.vgesture.PanStopListener;

public class ConditionalPanStop implements PanStopListener{
	private Conditional conditional;
	private PanStopListener listener;
	
	public ConditionalPanStop(Conditional conditional, PanStopListener listener) {
		this.conditional = conditional;
		this.listener = listener;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		if(conditional.isConditionMet()) {
			listener.panStop(x, y, pointer, button);
		}
		return false;
	}
}
