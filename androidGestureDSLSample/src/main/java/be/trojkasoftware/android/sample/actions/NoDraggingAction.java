package be.trojkasoftware.android.sample.actions;

import android.graphics.Point;

import be.trojkasoftware.android.ScreenVector;
import be.trojkasoftware.android.gestures.GestureActionBase;
import be.trojkasoftware.android.gestures.GestureEvent;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.sample.AndroidGestureDSLSampleView;

public class NoDraggingAction extends GestureActionBase<AndroidGestureDSLSampleView> {

	public NoDraggingAction(AndroidGestureDSLSampleView view) {
		super(view);
	}
	
	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		long timeStamp = motion.getTime(); 
		String message = "NoDraggingAction: " + ((Long)timeStamp).toString();
		//getTouchedView().drawMessage(message);
	}
	
	String message;
}