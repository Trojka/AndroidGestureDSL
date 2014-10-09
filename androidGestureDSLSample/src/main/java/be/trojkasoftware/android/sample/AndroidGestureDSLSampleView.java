package be.trojkasoftware.android.sample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import be.trojkasoftware.android.ScreenVector;
import be.trojkasoftware.android.gestures.TouchHandler;
import be.trojkasoftware.android.sample.gestures.ClickAndDoubleClickOnRectangleGesture;
import be.trojkasoftware.android.sample.gestures.ClickOnRectangleGesture;
import be.trojkasoftware.android.sample.gestures.DoubleClickOnRectangleGesture;
import be.trojkasoftware.android.sample.gestures.DragRectangleGesture;
import be.trojkasoftware.android.sample.gestures.DragRectangleOrShowMessageGesture;
import be.trojkasoftware.android.sample.gestures.LongClickOutsideRectangle;
import be.trojkasoftware.android.sample.gestures.SwipeRectangleGesture;

public class AndroidGestureDSLSampleView extends View {

	public static final String ENABLED_GESTURES = "EnabledGestures";

	public static final String CLICK_GESTURE = "ClickGesture";
	public static final String DBLCLICK_GESTURE = "DblClickGesture";
	public static final String CLICKDBLCLICK_GESTURE = "ClickDblClickGesture";
	public static final String DRAG_GESTURE = "DragGesture";
	public static final String LONGTOUCH_GESTURE = "LongTouchGesture";
	public static final String DRAGLONGTOUCH_GESTURE = "DragLongTouchGesture";
	
	public AndroidGestureDSLSampleView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	    touchHandler = new TouchHandler();
	    
	    ClickOnRectangleGesture clickOnRectangleBuilder = new ClickOnRectangleGesture(this);
	    DoubleClickOnRectangleGesture doubleClickOnRectangleBuilder = new DoubleClickOnRectangleGesture(this);
	    ClickAndDoubleClickOnRectangleGesture clickAndDoubleClickOnRectangleBuilder = new ClickAndDoubleClickOnRectangleGesture(this);
	    LongClickOutsideRectangle longClickOutsideRectangleBuilder = new LongClickOutsideRectangle(this);
	    DragRectangleGesture dragRectangleGestureBuilder = new DragRectangleGesture(this);
	    DragRectangleOrShowMessageGesture dragRectangleOrShowMessageGestureBuilder = new DragRectangleOrShowMessageGesture(this);
	    SwipeRectangleGesture swipeRectangleGesture = new SwipeRectangleGesture(this);
	    
		//touchHandler.addGesture(clickOnRectangleBuilder.create());
	    //touchHandler.addGesture(doubleClickOnRectangleBuilder.create());
		//touchHandler.addGesture(clickAndDoubleClickOnRectangleBuilder.create());
		//touchHandler.addGesture(longClickOutsideRectangleBuilder.create());
		//touchHandler.addGesture(dragRectangleGestureBuilder.create());
		//touchHandler.addGesture(dragRectangleOrShowMessageGestureBuilder.create());
		touchHandler.addGesture(swipeRectangleGesture.create());
	}
    
    public boolean onTouchEvent(MotionEvent motion)   { 
    	
    	touchHandler.onTouchEvent(motion);
    	
    	invalidate();
    	return true;  	
    }   

	protected void onDraw(Canvas canvas) {
    	Paint paint = new Paint();
    	paint.setColor(isOnRectangleResult ? Color.GREEN : Color.RED);
		if(rect == null)
			rect = getRect();
		
//    	Paint textPaint = new Paint();
//    	textPaint.setColor(isInvalidated ? Color.YELLOW : Color.GREEN);
//		canvas.drawText(drawMessage, 10, 10, textPaint);
//
//		canvas.drawText(isOnRectangleXCoord, 10, 25, paint);
//		canvas.drawText(isOnRectangleYCoord, 10, 35, paint);
		
		canvas.drawRect(rect, paint);
	}
	
	public boolean IsOnRectangle(ScreenVector position)
	{
//		float centerX = this.getWidth()/2;
//		float centerY = this.getHeight()/2;
//		
//		float size = this.getWidth()/4;
		
		if(rect == null)
			rect = getRect();

		isOnRectangleResult = false;
		if(rect.contains(position.x, position.y) )
		{
			isOnRectangleResult = true;
		}
		
		isOnRectangleXCoord = "X: l:" + ((Integer)(rect.left)).toString() + ",x:" + ((Integer)(position.x)).toString() + ",r:" +((Integer)(rect.right)).toString() ;
		
		return isOnRectangleResult;
	}
	
	public void showMessage(String message)
	{
		Toast msg = Toast.makeText(AndroidGestureDSLSampleView.this.getContext(), message, Toast.LENGTH_SHORT);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
		msg.show();		
	}
	
	public void drawMessage(String message)
	{
		drawMessage = message;
		invalidate();	
	}
	
	public void isInvalidated(boolean value)
	{
		isInvalidated = value;
	}
	
	public void setRectangleCenter(Point center)
	{
		rect = getRect(center.x, center.y);
		invalidate();
	}
	
	public Point getRectangleCenter()
	{
		if(rect == null)
			rect = getRect();
		
		return new Point(rect.centerX(), rect.centerY());
	}
	
	public String getRectangleDimensions()
	{
		Integer size = getSize();
		return "width:" + size.toString() + ", height:" + size.toString();
	}
	
	private Rect getRect()
	{
		int centerX = this.getWidth()/2;
		int centerY = this.getHeight()/2;
		
		return getRect(centerX, centerY);
	}
	
	private Rect getRect(int centerX, int centerY)
	{
		int size = getSize();
		
		return new Rect(centerX - (size/2), centerY - (size/2), centerX + (size/2), centerY + (size/2));
	}
	
	private int getSize()
	{
		return this.getWidth()/4;
	}
    
	Rect rect = null;
    TouchHandler touchHandler;
    
    boolean isOnRectangleResult = true;
    String isOnRectangleXCoord = "X: none";
    String isOnRectangleYCoord = "Y: none";
    
    String drawMessage = "None";
    boolean isInvalidated = false;
}
