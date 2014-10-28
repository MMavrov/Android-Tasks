package com.example.mavr0.gestureimage;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class GestureImage extends Activity {
    private int _xMovementDelta;
    private int _yMovementDelta;
    private double oldLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_image);

        final ImageView gunman = (ImageView) findViewById(R.id.gunman);

        gunman.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getActionMasked();
//                Log.w("event.toString()", event.toString());
//                Log.w("action ID", String.valueOf(action));

                switch (action) {

                    case (MotionEvent.ACTION_DOWN):
                    case (MotionEvent.ACTION_POINTER_DOWN):
                        if (event.getPointerCount() == 1) {
                            _xMovementDelta = (int) (event.getRawX() - v.getX());
                            _yMovementDelta = (int) (event.getRawY() - v.getY());

                        } else {
                            event.getActionIndex();
                            oldLength =
                                    Math.sqrt(Math.pow(Math.abs(event.getX(1) - event.getX(0)), 2) + Math.pow(Math.abs(event.getY(1) - event.getY(0)), 2));
                        }
                        break;

                    case (MotionEvent.ACTION_MOVE):
                        if (event.getPointerCount() == 1) {
                            v.setTranslationX(event.getRawX() - _xMovementDelta);
                            v.setTranslationY(event.getRawY() - _yMovementDelta);

//                            Log.d("getX()", Float.toString(event.getX()));
//                            Log.d("getY()", Float.toString(event.getY()));
                        } else {
                            double newLength = Math.sqrt(Math.pow(Math.abs(event.getX(1) - event.getX(0)), 2) + Math.pow(Math.abs(event.getY(1) - event.getY(0)), 2));
                            double ratio = newLength / oldLength;
                            v.setScaleX((float) (ratio));
                            v.setScaleY((float) (ratio));
                            v.invalidate();

//                            Log.w("Value of oldLength ", Double.toString(oldLength));
//                            Log.w("Value of newLength ", Double.toString(newLength));
                            Log.w("event.getX(0) ", Double.toString(event.getX(0)));
                            Log.w("event.getY(0) ", Double.toString(event.getY(0)));
                            Log.w("event.getX(1) ", Double.toString(event.getX(1)));
                            Log.w("event.getY(1) ", Double.toString(event.getY(1)));
//                            Log.w("rawX", Float.toString(event.getRawX()));
//                            Log.w("rawY", Float.toString(event.getRawY()));
                        }
                        break;
                    }
                return true;
            }
        });
    }
}
