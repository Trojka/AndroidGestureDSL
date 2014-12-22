package be.trojkasoftware.android.gestures.test.data;

import java.util.List;

import android.view.View;
import be.trojkasoftware.android.gestures.IGestureAction;
import be.trojkasoftware.android.gestures.IGestureCondition;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.dsl.GestureBuilder;
import be.trojkasoftware.android.gestures.test.utils.EvaluateCondition;
import be.trojkasoftware.android.gestures.test.utils.InsertMessageAction;

public class TouchHandlerSingleGestureConditionalTestsGestureBuilder extends GestureBuilder<View> {
	
	public TouchHandlerSingleGestureConditionalTestsGestureBuilder()
	{
		super(null);
	}
	
	public TouchGesture createDownMoveUpSequence(List<String> messageList, boolean downResult, boolean moveResult, boolean upResult)
	{
		TouchGesture gesture = new TouchGesture("OrderOfEventsTouchGesture");
		
		this.Create(gesture).TouchDown()
				.If(getConditionWithResult(downResult, "EvaluateDown", messageList))
					.Do2(registerAction("DownTrue", messageList))
				.Else()
					.Do3(registerAction("DownFalse", messageList))
			.AndNext().Move()
				.If(getConditionWithResult(moveResult, "EvaluateMove", messageList))
					.Do2(registerAction("MoveTrue", messageList))
				.Else()
					.Do3(registerAction("MoveFalse", messageList))
			.AndNext().TouchUp()
				.If(getConditionWithResult(upResult, "EvaluateUp", messageList))
					.Do2(registerAction("UpTrue", messageList))
				.Else()
					.Do3(registerAction("UpFalse", messageList))
		;
		
		return gesture;
	}	
	
	IGestureCondition getConditionWithResult(boolean result, String message, List<String> messageList) {
		return new EvaluateCondition(getBase(), result, message, messageList);
	}

	IGestureAction registerAction(String message, List<String> messageList)
	{
		return new InsertMessageAction(getBase(), message, messageList);
	}

}