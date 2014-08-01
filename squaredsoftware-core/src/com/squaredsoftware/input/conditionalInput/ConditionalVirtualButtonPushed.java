package com.squaredsoftware.input.conditionalInput;

import com.squaredsoftware.input.button.VirtualButtonPressedListener;

public class ConditionalVirtualButtonPushed implements VirtualButtonPressedListener{
	Conditional conditional;
	VirtualButtonPressedListener listener;
	
	public ConditionalVirtualButtonPushed(Conditional conditional, VirtualButtonPressedListener listener) {
		this.conditional = conditional;
		this.listener = listener;
	}

	@Override
	public void keyPressed() {		
		if(conditional.isConditionMet()) {
			listener.keyPressed();
		}
	}
}