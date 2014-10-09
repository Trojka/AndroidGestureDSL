package be.trojkasoftware.android.sample.conditions;

import java.util.List;

import be.trojkasoftware.android.gestures.GestureConditionBase;
import be.trojkasoftware.android.gestures.GestureEvent;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.sample.AndroidGestureDSLSampleView;

public class OnRectangleCondition extends GestureConditionBase<AndroidGestureDSLSampleView> {

	public OnRectangleCondition(AndroidGestureDSLSampleView view) {
		super(view);
	}
	
	@Override
	public boolean checkCondition(GestureEvent motion, TouchGesture gesture) {
		return getTouchBase().IsOnRectangle(motion.getPosition());
	}

}
