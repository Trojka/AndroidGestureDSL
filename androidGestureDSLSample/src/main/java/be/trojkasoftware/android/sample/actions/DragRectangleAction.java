package be.trojkasoftware.android.sample.actions;

import android.graphics.Point;

import be.trojkasoftware.android.ScreenVector;
import be.trojkasoftware.android.gestures.GestureActionBase;
import be.trojkasoftware.android.gestures.GestureEvent;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.sample.AndroidGestureDSLSampleView;

public class DragRectangleAction extends GestureActionBase<AndroidGestureDSLSampleView> {

	public DragRectangleAction(AndroidGestureDSLSampleView view) {
		super(view);
	}
	
	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		ScreenVector currentPosition = motion.getPosition(); 
		Point hitOffset = (Point)gesture.getContext(RegisterRectangleHitPointAction.RECTANGLE_CENTER_HITOFFSET);
		Point newRectangleCenter = new Point(currentPosition.x + hitOffset.x, currentPosition.y + hitOffset.y);
		getTouchedView().setRectangleCenter(newRectangleCenter);

		long timeStamp = motion.getTime(); 
		String message = "DragRectangleAction: " + ((Long)timeStamp).toString();
		//getTouchedView().drawMessage(message);
	}
	
	String message;
}
