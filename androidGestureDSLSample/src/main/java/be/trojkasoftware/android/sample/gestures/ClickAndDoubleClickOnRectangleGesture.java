package be.trojkasoftware.android.sample.gestures;

import android.telephony.SignalStrength;

import be.trojkasoftware.android.gestures.IGestureAction;
import be.trojkasoftware.android.gestures.IGestureCondition;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.dsl.GestureBuilder;
import be.trojkasoftware.android.sample.AndroidGestureDSLSampleView;
import be.trojkasoftware.android.sample.actions.ShowMessageAction;
import be.trojkasoftware.android.sample.conditions.OnRectangleCondition;


public class ClickAndDoubleClickOnRectangleGesture extends GestureBuilder<AndroidGestureDSLSampleView> {
	
	public ClickAndDoubleClickOnRectangleGesture(AndroidGestureDSLSampleView view)
	{
		super(view);
	}
	
	public TouchGesture create()
	{
		TouchGesture gesture = new TouchGesture("ClickAndDoubleClickOnRectangleGesture");
		
		this.Create(gesture).TouchDown()
				.If(OnRectangle())
			.AndNext().CanMove()
				.If(within().milliMeters(2).fromTouchDown(1))
			.AndNext().TouchUp()
				.Do1(after().seconds(1).Do(
						ShowMessage("You clicked on the rectangle"),
						gestureIsCompleted()))
			.AndNext().TouchDown()
				.Do1(endCurrentTimer())
			.AndNext().CanMove()
				.If(within().milliMeters(2).fromTouchDown(1))
			.AndNext().TouchUp()
				.If(within().seconds(2).fromTouchDown(1))
					.Do2(ShowMessage("You doubleclicked on the rectangle"))
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
