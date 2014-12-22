package be.trojkasoftware.android.gestures.test.utils;

import android.view.View;

import java.util.List;

import be.trojkasoftware.android.gestures.GestureConditionBase;
import be.trojkasoftware.android.gestures.GestureEvent;
import be.trojkasoftware.android.gestures.TouchGesture;

public class EvaluateCondition extends GestureConditionBase<View> {

    List<String> messageList;
    String message;

	boolean mResult;

	public EvaluateCondition(View base, boolean result, String message, List<String> messageList) {
		super(base);
		
		mResult = result;
	}

	@Override
	public boolean checkCondition(GestureEvent motion, TouchGesture gesture) {
        messageList.add(message);
		return mResult;
	}

}
