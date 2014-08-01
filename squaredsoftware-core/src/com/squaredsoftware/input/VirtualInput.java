package com.squaredsoftware.input;

import com.squaredsoftware.input.button.VirtualButtonInput;
import com.squaredsoftware.input.button.VirtualButtonPressedListener;
import com.squaredsoftware.input.button.VirtualButtonReleasedListener;
import com.squaredsoftware.input.conditionalInput.ConditionalFling;
import com.squaredsoftware.input.conditionalInput.ConditionalInput;
import com.squaredsoftware.input.conditionalInput.ConditionalInputDef;
import com.squaredsoftware.input.conditionalInput.ConditionalLongPress;
import com.squaredsoftware.input.conditionalInput.ConditionalPan;
import com.squaredsoftware.input.conditionalInput.ConditionalPanStop;
import com.squaredsoftware.input.conditionalInput.ConditionalPinch;
import com.squaredsoftware.input.conditionalInput.ConditionalTap;
import com.squaredsoftware.input.conditionalInput.ConditionalTouchDown;
import com.squaredsoftware.input.conditionalInput.ConditionalTouchUp;
import com.squaredsoftware.input.conditionalInput.ConditionalVirtualButtonPushed;
import com.squaredsoftware.input.conditionalInput.ConditionalVirtualButtonReleased;
import com.squaredsoftware.input.conditionalInput.ConditionalZoom;
import com.squaredsoftware.input.vgesture.FlingListener;
import com.squaredsoftware.input.vgesture.LongPressListener;
import com.squaredsoftware.input.vgesture.PanListener;
import com.squaredsoftware.input.vgesture.PanStopListener;
import com.squaredsoftware.input.vgesture.PinchListener;
import com.squaredsoftware.input.vgesture.TapListener;
import com.squaredsoftware.input.vgesture.TouchDownListener;
import com.squaredsoftware.input.vgesture.TouchUpListener;
import com.squaredsoftware.input.vgesture.VirtualGestureInput;
import com.squaredsoftware.input.vgesture.ZoomListener;

public class VirtualInput {
	private VirtualButtonInput vKeys;
	private VirtualGestureInput gestureInput;
	
	public VirtualInput() {
		vKeys = new VirtualButtonInput();
		gestureInput = new VirtualGestureInput();
	}
	
	public void initDriver(InputDriver driver) {
		driver.setVKeys(vKeys);
		driver.setGestureInput(gestureInput);
	}
	
	public void mapVKey(String vKeyName, int keyCode) {
		vKeys.mapVKey(vKeyName, keyCode);
	}
	
	public boolean isKeyPressed(String vKeyName) {
		return vKeys.isPressed(vKeyName);
	}
	
	public void addConditionalKeyPressed(String triggerKey, ConditionalInputDef condition, VirtualButtonPressedListener listener) {
		vKeys.addKeyPressedListener(triggerKey, new ConditionalVirtualButtonPushed(new ConditionalInput(condition, vKeys), listener));
	}
	
	public void addConditionalKeyReleased(String triggerKey, ConditionalInputDef condition, VirtualButtonReleasedListener listener) {
		vKeys.addKeyReleasedListener(triggerKey, new ConditionalVirtualButtonReleased(new ConditionalInput(condition, vKeys), listener));
	}
	
	public void addConditionalTouchUp(ConditionalInputDef condition, TouchUpListener listener) {
		gestureInput.addTouchUpListener(new ConditionalTouchUp(new ConditionalInput(condition, vKeys), listener));
	}
	
	public void addConditionalTouchDown(ConditionalInputDef condition, TouchDownListener listener) {
		gestureInput.addTouchDownListener(new ConditionalTouchDown(new ConditionalInput(condition, vKeys), listener));
	}
	
	public void addConditionalTap(ConditionalInputDef condition, TapListener listener) {
		gestureInput.addTapListener(new ConditionalTap(new ConditionalInput(condition, vKeys), listener));
	}
	
	public void addConditionalLongPress(ConditionalInputDef condition, LongPressListener listener) {
		gestureInput.addLongPressListener(new ConditionalLongPress(new ConditionalInput(condition, vKeys), listener));
	}
	
	public void addConditionalFling(ConditionalInputDef condition, FlingListener listener) {
		gestureInput.addFlingListener(new ConditionalFling(new ConditionalInput(condition, vKeys), listener));
	}
	
	public void addConditionalPan(ConditionalInputDef condition, PanListener listener) {
		gestureInput.addPanListener(new ConditionalPan(new ConditionalInput(condition, vKeys), listener));
	}
	
	public void addConditionalPanStop(ConditionalInputDef condition, PanStopListener listener) {
		gestureInput.addPanStopListener(new ConditionalPanStop(new ConditionalInput(condition, vKeys), listener));
	}
	
	public void addConditionalZoom(ConditionalInputDef condition, ZoomListener listener) {
		gestureInput.addZoomListener(new ConditionalZoom(new ConditionalInput(condition, vKeys), listener));
	}
	
	public void addConditionalPinch(ConditionalInputDef condition, PinchListener listener) {
		gestureInput.addPinchListener(new ConditionalPinch(new ConditionalInput(condition, vKeys), listener));
	}
	
	public void addKeyPressed(String key, VirtualButtonPressedListener listener) {
		vKeys.addKeyPressedListener(key, listener);
	}
	
	public void addKeyReleased(String key, VirtualButtonReleasedListener listener) {
		vKeys.addKeyReleasedListener(key, listener);
	}	
	
	public void addTouchDown(TouchDownListener listener) {
		gestureInput.addTouchDownListener(listener);
	}
	
	public void addTouchUp(TouchUpListener listener) {
		gestureInput.addTouchUpListener(listener);
	}
	
	public void addTap(TapListener listener) {
		gestureInput.addTapListener(listener);
	}
	
	public void addLongPress(LongPressListener listener) {
		gestureInput.addLongPressListener(listener);
	}
	
	public void addFling(FlingListener listener) {
		gestureInput.addFlingListener(listener);
	}
	
	public void addPan(PanListener listener) {
		gestureInput.addPanListener(listener);
	}
	
	public void addPanStop(PanStopListener listener) {
		gestureInput.addPanStopListener(listener);
	}
	
	public void addZoom(ZoomListener listener) {
		gestureInput.addZoomListener(listener);
	}
	
	public void addPinch(PinchListener listener) {
		gestureInput.addPinchListener(listener);
	}
}
