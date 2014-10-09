package be.trojkasoftware.android.gestures.test.utils;

import android.view.View;
import be.trojkasoftware.android.gestures.GestureConditionBase;
import be.trojkasoftware.android.gestures.GestureEvent;
import be.trojkasoftware.android.gestures.TouchGesture;

public class EvaluateCondition extends GestureConditionBase<View> {
	
	boolean mResult;

	public EvaluateCondition(View base, boolean result) {
		super(base);
		
		mResult = result;
	}

	@Override
	public boolean checkCondition(GestureEvent motion, TouchGesture gesture) {
		return mResult;
	}

}
