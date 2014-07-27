package com.squaredsoftware.input;

import com.squaredsoftware.input.button.VirtualButtonInput;
import com.squaredsoftware.input.vgesture.VirtualGestureInput;

public abstract class InputDriver {
	protected VirtualButtonInput vKeys;
	protected VirtualGestureInput gestureInput;
	
	void setVKeys(VirtualButtonInput vKeys) {
		this.vKeys = vKeys;
	}
	
	void setGestureInput(VirtualGestureInput gestureInput) {
		this.gestureInput = gestureInput;
	}
}
