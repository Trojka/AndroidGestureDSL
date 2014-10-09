package be.trojkasoftware.android.gestures.test.utils;

import java.util.List;

import android.view.View;
import be.trojkasoftware.android.gestures.GestureActionBase;
import be.trojkasoftware.android.gestures.GestureEvent;
import be.trojkasoftware.android.gestures.TouchGesture;

public class InsertMessageAction extends GestureActionBase<View> {

	List<String> messageList;
	String message;
	
	public InsertMessageAction(View view, String message, List<String> messageList) {
		super(view);
		
		this.messageList = messageList;
		this.message = message;
	}
	
	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		messageList.add(message);
	}

}
