package be.trojkasoftware.android.sample.gestures;

import be.trojkasoftware.android.gestures.IGestureAction;
import be.trojkasoftware.android.gestures.IGestureCondition;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.dsl.GestureBuilder;
import be.trojkasoftware.android.sample.AndroidGestureDSLSampleView;
import be.trojkasoftware.android.sample.actions.ShowMessageAction;
import be.trojkasoftware.android.sample.conditions.OnRectangleCondition;


public class LongClickOutsideRectangle extends GestureBuilder<AndroidGestureDSLSampleView> {
	
	public LongClickOutsideRectangle(AndroidGestureDSLSampleView view)
	{
		super(view);
	}
	
	public TouchGesture create()
	{
		TouchGesture gesture = new TouchGesture("LongClickOutsideRectangle");
		
		this.Create(gesture).TouchDown().If(not(OnRectangle()))
			.AndNext().CanMove()
				.If(within().milliMeters(2).fromTouchDown(1))
			.AndNext().TouchUp()
				.If(not(within().seconds(3).fromTouchDown(1)))
					.Do2(ShowMessage("You longclicked outside the rectangle"))
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
