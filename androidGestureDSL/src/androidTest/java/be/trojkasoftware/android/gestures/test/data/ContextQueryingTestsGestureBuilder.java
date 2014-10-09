package be.trojkasoftware.android.gestures.test.data;

import java.util.List;

import android.view.View;
import be.trojkasoftware.android.gestures.IGestureAction;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.dsl.GestureBuilder;
import be.trojkasoftware.android.gestures.test.utils.InsertMessageAction;

public class ContextQueryingTestsGestureBuilder extends GestureBuilder<View> {
	
	public ContextQueryingTestsGestureBuilder()
	{
		super(null);
	}
	
//	public ContextQueryingTestsGestureBuilder(GestureView view)
//	{
//		super(view);
//	}
	
	public TouchGesture createDownMoveUpSequence()
	{
		TouchGesture gesture = new TouchGesture("OrderOfEventsTouchGesture");
		
		this.Create(gesture).TouchDown()
			.AndNext().Move()
			.AndNext().TouchUp()
		;
		
		return gesture;
	}	
	
	public TouchGesture createDownCanMoveUpSequence()
	{
		TouchGesture gesture = new TouchGesture("OrderOfEventsTouchGesture");
		
		this.Create(gesture).TouchDown()
			.AndNext().CanMove()
			.AndNext().TouchUp()
		;
		
		return gesture;
	}	

}
