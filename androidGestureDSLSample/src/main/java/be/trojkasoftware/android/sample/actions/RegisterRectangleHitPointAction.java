package be.trojkasoftware.android.sample.actions;

import android.graphics.Point;

import be.trojkasoftware.android.ScreenVector;
import be.trojkasoftware.android.gestures.GestureActionBase;
import be.trojkasoftware.android.gestures.GestureEvent;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.TouchHandler;
import be.trojkasoftware.android.sample.AndroidGestureDSLSampleView;

public class RegisterRectangleHitPointAction extends GestureActionBase<AndroidGestureDSLSampleView> {
	
	public static String RECTANGLE_CENTER_HITOFFSET = "RECTANGLE_CENTER_HITOFFSET";

	public RegisterRectangleHitPointAction(AndroidGestureDSLSampleView view) {
		super(view);
	}
	
	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		Point rectangleCenter = getTouchedView().getRectangleCenter();
		ScreenVector touchDownPoint = motion.getPosition();//(ScreenVector)gesture.getContext(TouchHandler.ActionDownPos);
		
		Point hitOffset = new Point(rectangleCenter.x - touchDownPoint.x, rectangleCenter.y - touchDownPoint.y);
		
		gesture.addContext(RECTANGLE_CENTER_HITOFFSET, hitOffset);
	}
	
	String message;
}
