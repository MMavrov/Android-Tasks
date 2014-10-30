package com.example.mavr0.gestureimage;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class GestureImage extends Activity {
    private ImageView gunman;

    private int firstTouchX;
    private int firstTouchY;

    private double firstTouchLength;

    private double rotation;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        switch (action) {
        case (MotionEvent.ACTION_DOWN):
        case (MotionEvent.ACTION_POINTER_DOWN):
            if (event.getPointerCount() == 1) {
                firstTouchX = (int) event.getX();
                firstTouchY = (int) event.getY();

            } else {
                firstTouchX = (int) (event.getX(1) + event.getX(0))/2;
                firstTouchY = (int) (event.getY(1) + event.getY(0))/2;

                firstTouchLength = Math.sqrt(Math.pow(Math.abs(event.getX(1) - event.getX(0)), 2)
                                     + Math.pow(Math.abs(event.getY(1) - event.getY(0)), 2));

                float xDiff = event.getX(1) - event.getX(0);
                float yDiff = event.getY(1) - event.getY(0);

                rotation = Math.toDegrees(Math.atan2(yDiff, xDiff));
            }
            break;

        case (MotionEvent.ACTION_MOVE):
            if (event.getPointerCount() == 1) {
                MoveView(event.getX(), event.getY());
            } else {
                MoveView((event.getX(1) + event.getX(0))/2, (event.getY(1) + event.getY(0))/2);

                ScaleView(event.getX(0), event.getX(1), event.getY(0), event.getY(1));

                RotateView(event.getX(0), event.getX(1), event.getY(0), event.getY(1));
            }
            break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_image);

        gunman = (ImageView) findViewById(R.id.gunman);
    }

    private void MoveView(float X, float Y) {
        // Moving
        float dX = X - firstTouchX;
        float dY = Y - firstTouchY;

        firstTouchX = (int) X;
        firstTouchY = (int) Y;

        gunman.setTranslationX(gunman.getX() + dX);
        gunman.setTranslationY(gunman.getY() + dY);
    }

    private void ScaleView(float x0, float x1, float y0, float y1) {

        double newLength = Math.sqrt(Math.pow(Math.abs(x1 - x0), 2)
                + Math.pow(Math.abs(y1 - y0), 2));

        double dL = (newLength / firstTouchLength) - 1;

        gunman.setScaleX((float) (gunman.getScaleX() + dL));
        gunman.setScaleY((float) (gunman.getScaleY() + dL));

        firstTouchLength = newLength;
    }

    private void RotateView(float x0, float x1, float y0, float y1) {
        float xDiff = x1 - x0;
        float yDiff = y1 - y0;

        double currentRotation = Math.toDegrees(Math.atan2(yDiff, xDiff));

        double rotationAngle = currentRotation - rotation;

        gunman.setRotation(gunman.getRotation() + (float) rotationAngle);

        rotation = currentRotation;
    }
}
