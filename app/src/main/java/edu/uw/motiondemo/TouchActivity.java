package edu.uw.motiondemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;

public class TouchActivity extends Activity {

    private static final String TAG = "Touch";

    private DrawingSurfaceView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (DrawingSurfaceView)findViewById(R.id.drawingView);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        //handle action
        int action = MotionEventCompat.getActionMasked(event);
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                Log.v(TAG,"Touch!");
                return true;

            case (MotionEvent.ACTION_MOVE) : //move
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
}
