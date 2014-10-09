package be.trojkasoftware.android.gestures.behaviourtest;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.TouchHandler;
import be.trojkasoftware.android.gestures.test.data.TouchHandlerSingleGestureTestsGestureBuilder;
import android.os.SystemClock;
import android.test.AndroidTestCase;
import android.view.MotionEvent;

public class TouchHandlerSingleGesureTests extends AndroidTestCase {
    private List<String> messageList;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	    messageList = new ArrayList<String>();
	}
	
	public void testOrderOfEventsDMU() {
		TouchHandlerSingleGestureTestsGestureBuilder builder = new TouchHandlerSingleGestureTestsGestureBuilder();
		TouchGesture gesture = builder.createDownMoveUpSequence(messageList);

		TouchHandler touchHandler = new TouchHandler();
		touchHandler.addGesture(gesture);
		
		messageList.clear();
		
		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		MotionEvent eventDown = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 0, 0, 0);
		MotionEvent eventMove = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, 1, 1, 0);
		MotionEvent eventUp = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 1, 1, 0);
		
		touchHandler.handleEvent(eventDown);
		touchHandler.handleEvent(eventMove);
		touchHandler.handleEvent(eventUp);

		Assert.assertEquals(3, messageList.size());
		
		Assert.assertEquals(TouchHandlerSingleGestureTestsGestureBuilder.TOUCH_DOWN, messageList.get(0));
		Assert.assertEquals(TouchHandlerSingleGestureTestsGestureBuilder.TOUCH_MOVE, messageList.get(1));
		Assert.assertEquals(TouchHandlerSingleGestureTestsGestureBuilder.TOUCH_UP, messageList.get(2));
		
		eventDown.recycle();
		eventMove.recycle();
		eventUp.recycle();
	}	
	
	public void testOrderOfEventsDMMMU() {
		TouchHandlerSingleGestureTestsGestureBuilder builder = new TouchHandlerSingleGestureTestsGestureBuilder();
		TouchGesture gesture = builder.createDownMoveUpSequence(messageList);

		TouchHandler touchHandler = new TouchHandler();
		touchHandler.addGesture(gesture);
		
		messageList.clear();
		
		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		MotionEvent eventDown = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 0, 0, 0);
		MotionEvent eventMove = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, 1, 1, 0);
		MotionEvent eventUp = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 1, 1, 0);
		
		touchHandler.handleEvent(eventDown);
		touchHandler.handleEvent(eventMove);
		touchHandler.handleEvent(eventMove);
		touchHandler.handleEvent(eventMove);
		touchHandler.handleEvent(eventUp);

		Assert.assertEquals(5, messageList.size());
		
		Assert.assertEquals(TouchHandlerSingleGestureTestsGestureBuilder.TOUCH_DOWN, messageList.get(0));
		Assert.assertEquals(TouchHandlerSingleGestureTestsGestureBuilder.TOUCH_MOVE, messageList.get(1));
		Assert.assertEquals(TouchHandlerSingleGestureTestsGestureBuilder.TOUCH_MOVE, messageList.get(2));
		Assert.assertEquals(TouchHandlerSingleGestureTestsGestureBuilder.TOUCH_MOVE, messageList.get(3));
		Assert.assertEquals(TouchHandlerSingleGestureTestsGestureBuilder.TOUCH_UP, messageList.get(4));
		
		eventDown.recycle();
		eventMove.recycle();
		eventUp.recycle();
	}	
	
	public void testGestureValidTransitions() {
		TouchHandlerSingleGestureTestsGestureBuilder builder = new TouchHandlerSingleGestureTestsGestureBuilder();
		TouchGesture gesture = builder.createDownMoveUpSequence(messageList);

		TouchHandler touchHandler = new TouchHandler();
		touchHandler.addGesture(gesture);
		
		messageList.clear();
		
		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		MotionEvent eventDown = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 0, 0, 0);
		MotionEvent eventMove = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, 1, 1, 0);
		MotionEvent eventUp = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 1, 1, 0);
		
		touchHandler.handleEvent(eventDown);		
		Assert.assertTrue(gesture.isValid());
		
		touchHandler.handleEvent(eventMove);		
		Assert.assertTrue(gesture.isValid());
		
		touchHandler.handleEvent(eventUp);		
		Assert.assertFalse(gesture.isValid());

		
		eventDown.recycle();
		eventMove.recycle();
		eventUp.recycle();
	}
	
	public void testGestureInValidTransitions() {
		TouchHandlerSingleGestureTestsGestureBuilder builder = new TouchHandlerSingleGestureTestsGestureBuilder();
		TouchGesture gesture = builder.createDownMoveUpSequence(messageList);

		TouchHandler touchHandler = new TouchHandler();
		touchHandler.addGesture(gesture);
		
		messageList.clear();
		
		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		MotionEvent eventDown = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 0, 0, 0);
		MotionEvent eventUp = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 1, 1, 0);
		
		touchHandler.handleEvent(eventDown);		
		Assert.assertTrue(gesture.isValid());
		
		touchHandler.handleEvent(eventUp);		
		Assert.assertFalse(gesture.isValid());
		
		touchHandler.handleEvent(eventDown);		
		Assert.assertFalse(gesture.isValid());
		
		eventDown.recycle();
		eventUp.recycle();
	}
	
	public void testStopInterpretationWhenInvalid() {
		TouchHandlerSingleGestureTestsGestureBuilder builder = new TouchHandlerSingleGestureTestsGestureBuilder();
		TouchGesture gesture = builder.createDownMoveUpSequence(messageList);

		TouchHandler touchHandler = new TouchHandler();
		touchHandler.addGesture(gesture);
		
		messageList.clear();
		
		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		MotionEvent eventDown = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 0, 0, 0);
		MotionEvent eventUp = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 1, 1, 0);
		
		touchHandler.handleEvent(eventDown);
		touchHandler.handleEvent(eventUp);

		Assert.assertEquals(1, messageList.size());
		
		Assert.assertEquals(TouchHandlerSingleGestureTestsGestureBuilder.TOUCH_DOWN, messageList.get(0));
		
		eventDown.recycle();
		eventUp.recycle();
	}
	
	public void testOptionalMoveKeepsGestureValid() {
		TouchHandlerSingleGestureTestsGestureBuilder builder = new TouchHandlerSingleGestureTestsGestureBuilder();
		TouchGesture gesture = builder.createDownCanMoveUpSequence(messageList);

		TouchHandler touchHandler = new TouchHandler();
		touchHandler.addGesture(gesture);
		
		messageList.clear();
		
		long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		MotionEvent eventDown = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 0, 0, 0);
		MotionEvent eventUp = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 1, 1, 0);
		
		touchHandler.handleEvent(eventDown);
		Assert.assertTrue(gesture.isValid());		
		
		touchHandler.handleEvent(eventUp);
		Assert.assertFalse(gesture.isValid());

		Assert.assertEquals(2, messageList.size());
		
		Assert.assertEquals(TouchHandlerSingleGestureTestsGestureBuilder.TOUCH_DOWN, messageList.get(0));
		Assert.assertEquals(TouchHandlerSingleGestureTestsGestureBuilder.TOUCH_UP, messageList.get(1));
		
		eventDown.recycle();
		eventUp.recycle();
	}

}
