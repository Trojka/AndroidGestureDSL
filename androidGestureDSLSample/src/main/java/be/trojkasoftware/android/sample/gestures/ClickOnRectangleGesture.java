package be.trojkasoftware.android.sample.gestures;

import be.trojkasoftware.android.gestures.IGestureAction;
import be.trojkasoftware.android.gestures.IGestureCondition;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.dsl.GestureBuilder;
import be.trojkasoftware.android.sample.AndroidGestureDSLSampleView;
import be.trojkasoftware.android.sample.actions.ShowMessageAction;
import be.trojkasoftware.android.sample.conditions.OnRectangleCondition;


public class ClickOnRectangleGesture extends GestureBuilder<AndroidGestureDSLSampleView> {
	
	public ClickOnRectangleGesture(AndroidGestureDSLSampleView view)
	{
		super(view);
	}
	
	public TouchGesture create()
	{
		TouchGesture gesture = new TouchGesture("ClickOnRectangleGesture");
		
		this.Create(gesture).TouchDown()
				.If(OnRectangle())
			.AndNext().CanMove()
				.If(within().milliMeters(2).fromTouchDown(1))
			.AndNext().TouchUp()
				.If(within().seconds(1).fromTouchDown(1))
					.Do2(ShowMessage("You clicked on the rectangle"))
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
