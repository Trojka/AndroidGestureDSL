package be.trojkasoftware.android.sample.gestures;

import be.trojkasoftware.android.gestures.IGestureAction;
import be.trojkasoftware.android.gestures.IGestureCondition;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.dsl.GestureBuilder;
import be.trojkasoftware.android.sample.AndroidGestureDSLSampleView;
import be.trojkasoftware.android.sample.actions.ShowMessageAction;
import be.trojkasoftware.android.sample.conditions.OnRectangleCondition;


public class SwipeRectangleGesture extends GestureBuilder<AndroidGestureDSLSampleView> {
	
	public SwipeRectangleGesture(AndroidGestureDSLSampleView view)
	{
		super(view);
	}
	
	public TouchGesture create()
	{
		TouchGesture gesture = new TouchGesture("SwipeRectangleGesture");
		
		this.Create(gesture).TouchDown()
				.If(OnRectangle())
			.AndNext().Move()
				.Do1(nothing())
			.AndNext().TouchUp()
				.If(exceed().milliMeters(10).fromTouchDown(1))
				.AndIf(within().seconds(2).fromTouchDown(1))
					.Do2(ShowMessage("You swiped the rectangle"))
		;
		
		return gesture;
	}
	
	IGestureCondition OnRectangle()
	{
		return new OnRectangleCondition(getBase());
	}

	IGestureAction ShowMessage(String message)
	{
		return new ShowMessageAction(getBase(), message);
	}
}