package be.trojkasoftware.android.sample;

import be.trojkasoftware.android.sample.AndroidGestureDSLSampleView;


import android.app.Activity;
import android.os.Bundle;

public class AndroidGestureDSLSampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        view = new AndroidGestureDSLSampleView(this);
        
        setContentView(view);
    }
    
    private AndroidGestureDSLSampleView view;
}