package be.trojkasoftware.android.gestures.behaviourtest;

import java.util.ArrayList;

import junit.framework.Assert;
import be.trojkasoftware.android.ScreenVector;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.TouchHandler;
import be.trojkasoftware.android.gestures.test.data.ContextQueryingTestsGestureBuilder;
import be.trojkasoftware.android.gestures.test.data.TouchHandlerSingleGestureTestsGestureBuilder;

import android.os.SystemClock;
import android.test.AndroidTestCase;
import android.view.MotionEvent;

public class ContextQueryingTests extends AndroidTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testCoordinates() {
		ContextQueryingTestsGestureBuilder builder = new ContextQueryingTestsGestureBuilder();
		TouchGesture gesture = builder.createDownMoveUpSequence();

		TouchHandler touchHandler = new TouchHandler();
		touchHandler.addGesture(gesture);
		
		ScreenVector pos = null;

		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		MotionEvent eventDown = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 1, 2, 0);
		MotionEvent eventMove = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, 3, 4, 0);
		MotionEvent eventUp = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 5, 6, 0);
		
		touchHandler.handleEvent(eventDown);
		Assert.assertTrue(gesture.contextExists(TouchHandler.getEventId(TouchHandler.ActionDownPos, 1)));
		Assert.assertTrue(gesture.contextExists(TouchHandler.LastActionPos));
		
		pos = (ScreenVector)gesture.getContext(TouchHandler.getEventId(TouchHandler.ActionDownPos, 1));
		Assert.assertEquals(1, pos.x);
		Assert.assertEquals(2, pos.y);
		pos = (ScreenVector)gesture.getContext(TouchHandler.LastActionPos);
		Assert.assertEquals(1, pos.x);
		Assert.assertEquals(2, pos.y);
		
		touchHandler.handleEvent(eventMove);
		//Assert.assertTrue(gesture.contextExists(TouchHandler.getEventId(TouchHandler.ActionMovePos, 1)));
		Assert.assertTrue(gesture.contextExists(TouchHandler.LastActionPos));
		
		pos = (ScreenVector)gesture.getContext(TouchHandler.LastActionPos);
		Assert.assertEquals(3, pos.x);
		Assert.assertEquals(4, pos.y);
		
		touchHandler.handleEvent(eventUp);
		Assert.assertTrue(gesture.contextExists(TouchHandler.getEventId(TouchHandler.ActionUpPos, 1)));
		Assert.assertTrue(gesture.contextExists(TouchHandler.LastActionPos));
		
		pos = (ScreenVector)gesture.getContext(TouchHandler.getEventId(TouchHandler.ActionUpPos, 1));
		Assert.assertEquals(5, pos.x);
		Assert.assertEquals(6, pos.y);
		pos = (ScreenVector)gesture.getContext(TouchHandler.LastActionPos);
		Assert.assertEquals(5, pos.x);
		Assert.assertEquals(6, pos.y);

		eventDown.recycle();
		eventMove.recycle();
		eventUp.recycle();
	}	
	
	public void testCoordinatesOnlyExistAfterEvent() {
		ContextQueryingTestsGestureBuilder builder = new ContextQueryingTestsGestureBuilder();
		TouchGesture gesture = builder.createDownMoveUpSequence();

		TouchHandler touchHandler = new TouchHandler();
		touchHandler.addGesture(gesture);
		
		ScreenVector pos = null;

		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		MotionEvent eventDown = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 1, 2, 0);
		MotionEvent eventMove = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, 3, 4, 0);
		MotionEvent eventUp = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 5, 6, 0);
		
		touchHandler.handleEvent(eventDown);
		Assert.assertFalse(gesture.contextExists(TouchHandler.getEventId(TouchHandler.ActionUpPos, 1)));
		
		touchHandler.handleEvent(eventMove);
		Assert.assertFalse(gesture.contextExists(TouchHandler.getEventId(TouchHandler.ActionUpPos, 1)));

		touchHandler.handleEvent(eventUp);
		Assert.assertTrue(gesture.contextExists(TouchHandler.getEventId(TouchHandler.ActionUpPos, 1)));
		
		eventDown.recycle();
		eventMove.recycle();
		eventUp.recycle();
	}	
	
	public void testTimings() {
		ContextQueryingTestsGestureBuilder builder = new ContextQueryingTestsGestureBuilder();
		TouchGesture gesture = builder.createDownMoveUpSequence();

		TouchHandler touchHandler = new TouchHandler();
		touchHandler.addGesture(gesture);
		
		long actionTime = 0;

		long downTime = SystemClock.uptimeMillis();
		long seedTime = SystemClock.uptimeMillis();
		long downEventTime = seedTime;
		long moveEventTime = seedTime + 10;
		long upEventTime = seedTime + 20;
		MotionEvent eventDown = MotionEvent.obtain(downTime, downEventTime, MotionEvent.ACTION_DOWN, 1, 2, 0);
		MotionEvent eventMove = MotionEvent.obtain(downTime, moveEventTime, MotionEvent.ACTION_MOVE, 3, 4, 0);
		MotionEvent eventUp = MotionEvent.obtain(downTime, upEventTime, MotionEvent.ACTION_UP, 5, 6, 0);
		
		touchHandler.handleEvent(eventDown);
		Assert.assertTrue(gesture.contextExists(TouchHandler.getEventId(TouchHandler.ActionDownTime, 1)));
		Assert.assertTrue(gesture.contextExists(TouchHandler.LastActionTime));
		
		actionTime = (Long)gesture.getContext(TouchHandler.getEventId(TouchHandler.ActionDownTime, 1));
		Assert.assertEquals(downEventTime, actionTime);
		actionTime = (Long)gesture.getContext(TouchHandler.LastActionTime);
		Assert.assertEquals(downEventTime, actionTime);
		
		touchHandler.handleEvent(eventMove);
		//Assert.assertTrue(gesture.contextExists(TouchHandler.getEventId(TouchHandler.ActionMovePos, 1)));
		Assert.assertTrue(gesture.contextExists(TouchHandler.LastActionTime));
		
		actionTime = (Long)gesture.getContext(TouchHandler.LastActionTime);
		Assert.assertEquals(moveEventTime, actionTime);
		
		touchHandler.handleEvent(eventUp);
		Assert.assertTrue(gesture.contextExists(TouchHandler.getEventId(TouchHandler.ActionUpTime, 1)));
		Assert.assertTrue(gesture.contextExists(TouchHandler.LastActionTime));
		
		actionTime = (Long)gesture.getContext(TouchHandler.getEventId(TouchHandler.ActionUpTime, 1));
		Assert.assertEquals(upEventTime, actionTime);
		actionTime = (Long)gesture.getContext(TouchHandler.LastActionTime);
		Assert.assertEquals(upEventTime, actionTime);

		eventDown.recycle();
		eventMove.recycle();
		eventUp.recycle();
	}	
	
	public void testTimingsOnlyExistAfterEvent() {
		ContextQueryingTestsGestureBuilder builder = new ContextQueryingTestsGestureBuilder();
		TouchGesture gesture = builder.createDownMoveUpSequence();

		TouchHandler touchHandler = new TouchHandler();
		touchHandler.addGesture(gesture);
		
		ScreenVector pos = null;

		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		MotionEvent eventDown = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 1, 2, 0);
		MotionEvent eventMove = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, 3, 4, 0);
		MotionEvent eventUp = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 5, 6, 0);
		
		touchHandler.handleEvent(eventDown);
		Assert.assertFalse(gesture.contextExists(TouchHandler.getEventId(TouchHandler.ActionUpTime, 1)));
		
		touchHandler.handleEvent(eventMove);
		Assert.assertFalse(gesture.contextExists(TouchHandler.getEventId(TouchHandler.ActionUpTime, 1)));

		touchHandler.handleEvent(eventUp);
		Assert.assertTrue(gesture.contextExists(TouchHandler.getEventId(TouchHandler.ActionUpTime, 1)));
		
		eventDown.recycle();
		eventMove.recycle();
		eventUp.recycle();
	}	

}
