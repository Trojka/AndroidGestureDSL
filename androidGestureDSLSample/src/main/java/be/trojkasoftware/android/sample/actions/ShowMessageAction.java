package be.trojkasoftware.android.sample.actions;

import java.util.List;

import be.trojkasoftware.android.gestures.GestureActionBase;
import be.trojkasoftware.android.gestures.GestureEvent;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.sample.AndroidGestureDSLSampleView;

public class ShowMessageAction extends GestureActionBase<AndroidGestureDSLSampleView> {

	public ShowMessageAction(AndroidGestureDSLSampleView view, String message) {
		super(view);
		
		this.message = message;
	}
	
	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		getTouchedView().showMessage(message);
	}
	
	String message;
}
