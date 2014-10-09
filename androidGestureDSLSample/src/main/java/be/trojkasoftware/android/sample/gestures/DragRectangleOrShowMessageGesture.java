package be.trojkasoftware.android.sample.gestures;

import be.trojkasoftware.android.gestures.IGestureAction;
import be.trojkasoftware.android.gestures.IGestureCondition;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.dsl.GestureBuilder;
import be.trojkasoftware.android.sample.AndroidGestureDSLSampleView;
import be.trojkasoftware.android.sample.actions.DragRectangleAction;
import be.trojkasoftware.android.sample.actions.NoDraggingAction;
import be.trojkasoftware.android.sample.actions.RegisterRectangleHitPointAction;
import be.trojkasoftware.android.sample.actions.ShowMessageAction;
import be.trojkasoftware.android.sample.conditions.OnRectangleCondition;


public class DragRectangleOrShowMessageGesture extends GestureBuilder<AndroidGestureDSLSampleView> {
	
	public DragRectangleOrShowMessageGesture(AndroidGestureDSLSampleView view)
	{
		super(view);
	}
	
	public TouchGesture create()
	{
		TouchGesture gesture = new TouchGesture("DragRectangleGesture");
		
		this.Create(gesture).TouchDown().If(OnRectangle())
				.Do2(RegisterRectangleHitPoint(), 
						after().seconds(5).Do(ShowRectangleDimensions()))
			.AndNext()
				.Move()
				.If(not(within().milliMeters(2).fromTouchDown(1)))
					.Do2(DragRectangle(), 
							endCurrentTimer(), 
							after().seconds(5).Do(ShowRectangleDimensions()))
				.Else()
					.Do3(nothing())
			.AndNext()
				.TouchUp()
				.Do1(endCurrentTimer())
			.AndFinally(endCurrentTimer())
		;

		
		return gesture;
	}
	
	IGestureCondition OnRectangle()
	{
		return new OnRectangleCondition(getBase());
	}
	
	IGestureAction RegisterRectangleHitPoint()
	{
		return new RegisterRectangleHitPointAction(getBase());
	}
	
	IGestureAction DragRectangle()
	{
		return new DragRectangleAction(getBase());
	}
	
	IGestureAction NoDragging()
	{
		return new NoDraggingAction(getBase());
	}

	IGestureAction ShowMessage(String message)
	{
		return new ShowMessageAction(getBase(), message);
	}

	IGestureAction ShowRectangleDimensions()
	{
		return new ShowMessageAction(getBase(), getBase().getRectangleDimensions());
	}
}
