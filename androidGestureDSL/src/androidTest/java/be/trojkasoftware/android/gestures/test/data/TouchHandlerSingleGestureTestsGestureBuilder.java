package be.trojkasoftware.android.gestures.test.data;

import java.util.List;

import android.view.View;
import be.trojkasoftware.android.gestures.IGestureAction;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.dsl.GestureBuilder;
import be.trojkasoftware.android.gestures.test.utils.InsertMessageAction;

public class TouchHandlerSingleGestureTestsGestureBuilder extends GestureBuilder<View> {
	
	public static String TOUCH_DOWN = "TouchDown";
	public static String TOUCH_MOVE = "TouchMove";
	public static String TOUCH_UP = "TouchUp";	
	
	public TouchHandlerSingleGestureTestsGestureBuilder()
	{
		super(null);
	}
	
//	public TouchHandlerTestsGestureBuilder(GestureView view)
//	{
//		super(view);
//	}
	
	public TouchGesture createDownMoveUpSequence(List<String> messageList)
	{
		TouchGesture gesture = new TouchGesture("OrderOfEventsTouchGesture");
		
		this.Create(gesture).TouchDown()
				.Do1(registerTouchDownAction(messageList))
			.AndNext().Move()
				.Do1(registerMoveAction(messageList))
			.AndNext().TouchUp()
				.Do1(registerTouchUpAction(messageList))
		;
		
		return gesture;
	}	
	
	public TouchGesture createDownCanMoveUpSequence(List<String> messageList)
	{
		TouchGesture gesture = new TouchGesture("OrderOfEventsTouchGesture");
		
		this.Create(gesture).TouchDown()
				.Do1(registerTouchDownAction(messageList))
			.AndNext().CanMove()
				.Do1(registerMoveAction(messageList))
			.AndNext().TouchUp()
				.Do1(registerTouchUpAction(messageList))
		;
		
		return gesture;
	}	

	IGestureAction registerTouchDownAction(List<String> messageList)
	{
		return new InsertMessageAction(getBase(), TOUCH_DOWN, messageList);
	}

	IGestureAction registerMoveAction(List<String> messageList)
	{
		return new InsertMessageAction(getBase(), TOUCH_MOVE, messageList);
	}

	IGestureAction registerTouchUpAction(List<String> messageList)
	{
		return new InsertMessageAction(getBase(), TOUCH_UP, messageList);
	}
}
