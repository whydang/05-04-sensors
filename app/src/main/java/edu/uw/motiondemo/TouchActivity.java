package edu.uw.motiondemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class TouchActivity extends Activity {

    private static final String TAG = "Touch";

    private DrawingSurfaceView view;
    private GestureDetector dectector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (DrawingSurfaceView)findViewById(R.id.drawingView);

        dectector = new GestureDetector(this, new MyGestureListener());


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean gestured = dectector.onTouchEvent(event);


        float x = event.getX();
        float y = event.getY();

        // not always the same index everytime
        int pointerIndex = MotionEventCompat.getActionIndex(event);
        int pointerID = MotionEventCompat.getPointerId(event, pointerIndex);



        //handle action
        int action = MotionEventCompat.getActionMasked(event);
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                Log.v(TAG,"Touch!");

                view.addTouch(pointerID, event.getX(pointerIndex), event.getY(pointerIndex));
                return true;
            case MotionEvent.ACTION_POINTER_DOWN: // multiple fingers down
                view.addTouch(pointerID, event.getX(pointerIndex), event.getY(pointerIndex));

                return true;
            case MotionEvent.ACTION_POINTER_UP: // single finger lifted up
                view.removeTouch(pointerID);

                return true;

            case MotionEvent.ACTION_UP: // last finger leaves, doesn't need to be the same finger place
                view.removeTouch(pointerID);

                return true;

            case (MotionEvent.ACTION_MOVE) : //move

                // loop through all pointser in the event and move them
                // given that one of them has moved


                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

    public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // do something

            view.ball.dx = 0.3f * velocityX; // num of pixels moved, so scale down
            view.ball.dy = 0.3f * velocityY;



            // i've handled this
            return true;
        }
    }
}
