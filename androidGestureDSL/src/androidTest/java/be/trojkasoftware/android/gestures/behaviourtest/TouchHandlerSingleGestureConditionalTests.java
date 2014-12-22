package be.trojkasoftware.android.gestures.behaviourtest;

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;

public class TouchHandlerSingleGestureConditionalTests extends AndroidTestCase {
    private List<String> messageList;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	    messageList = new ArrayList<String>();
	}

    public void testDownAndConditional() {
    }
}
