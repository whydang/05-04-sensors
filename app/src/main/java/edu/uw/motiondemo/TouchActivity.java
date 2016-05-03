package edu.uw.motiondemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class TouchActivity extends Activity {

    private static final String TAG = "Touch";

    private DrawingSurfaceView view;

    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (DrawingSurfaceView)findViewById(R.id.drawingView);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mDetector.onTouchEvent(event); //detect flings

        float x = event.getX();
        float y = event.getY();

        //which finger
        int pointerIndex = MotionEventCompat.getActionIndex(event); //which finger came down
        int pointerId = MotionEventCompat.getPointerId(event, pointerIndex); //consistent id

        //handle action
        int action = MotionEventCompat.getActionMasked(event);
        switch(action) {
            case MotionEvent.ACTION_DOWN: //put finger down
                Log.v(TAG,"First finger down!");
                view.addTouch(pointerId, event.getX(pointerIndex), event.getY(pointerIndex));
                //view.addTouch(pointerId, MotionEventCompat.getX(event, pointerIndex), MotionEventCompat.getY(event, pointerIndex));
                return true;

            case MotionEvent.ACTION_POINTER_DOWN: //more fingers down
                Log.v(TAG,"Another finger down!");
                view.addTouch(pointerId, event.getX(pointerIndex), event.getY(pointerIndex));
                return true;

            case MotionEvent.ACTION_POINTER_UP:
                Log.v(TAG,"Finger up!");
                view.removeTouch(pointerId);
                return true;

            case MotionEvent.ACTION_UP:
                Log.v(TAG,"Last finger up!");
                view.removeTouch(pointerId);
                return true;

            case (MotionEvent.ACTION_MOVE) : //move finger
                //iterate through all the pointers and update all of them on a "move"
                int pointerCount = event.getPointerCount();
                for(int i=0; i < pointerCount; i++){
                    int pId  = event.getPointerId(i);
                    view.moveTouch(pId, event.getX(i), event.getY(i));
                }
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            Log.v(TAG, "On down");

            return true; //we're processing this event
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            float scaleFactor = .03f;

            //fling!
            Log.v(TAG, "Fling! "+ velocityX + ", " + velocityY);
            view.ball.dx = -1*velocityX*scaleFactor;
            view.ball.dy = -1*velocityY*scaleFactor;

            return true; //we got this
        }
    }
}
