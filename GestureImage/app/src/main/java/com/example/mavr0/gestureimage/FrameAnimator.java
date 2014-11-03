package com.example.mavr0.gestureimage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FrameAnimator extends Activity {
    private ImageView image;

    private int oldX;
    private int oldY;

    private double oldLength;

    private double oldRotation;

    private Button saveButton;
    private Button playButton;

    List<Animator> animations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_image);

        image = (ImageView) findViewById(R.id.gunman);
        image.setY(50f);

        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(saveClickListener);

        playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(playClickListener);

        animations = new ArrayList<Animator>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        switch (action) {
        case (MotionEvent.ACTION_DOWN):
        case (MotionEvent.ACTION_POINTER_DOWN):
            if (event.getPointerCount() == 1) {
                oldX = (int) event.getX();
                oldY = (int) event.getY();

            } else {
                oldX = (int) (event.getX(1) + event.getX(0))/2;
                oldY = (int) (event.getY(1) + event.getY(0))/2;

                oldLength = Math.sqrt(Math.pow(Math.abs(event.getX(1) - event.getX(0)), 2)
                                     + Math.pow(Math.abs(event.getY(1) - event.getY(0)), 2));

                float xDiff = event.getX(1) - event.getX(0);
                float yDiff = event.getY(1) - event.getY(0);

                oldRotation = Math.toDegrees(Math.atan2(yDiff, xDiff));
            }
            break;

        case (MotionEvent.ACTION_MOVE):
            if (event.getPointerCount() == 1) {
                MoveImage(event.getX(), event.getY());
            } else {
                MoveImage((event.getX(1) + event.getX(0)) / 2, (event.getY(1) + event.getY(0)) / 2);

                ScaleImage(event.getX(0), event.getX(1), event.getY(0), event.getY(1));

                RotateImage(event.getX(0), event.getX(1), event.getY(0), event.getY(1));
            }
            break;
        }
        return false;
    }

    private void MoveImage(float X, float Y) {
        float dX = X - oldX;
        float dY = Y - oldY;

        oldX = (int) X;
        oldY = (int) Y;

        image.setTranslationX(image.getX() + dX);
        image.setTranslationY(image.getY() + dY);
    }

    private void ScaleImage(float x0, float x1, float y0, float y1) {
        double newLength = Math.sqrt(Math.pow(Math.abs(x1 - x0), 2)
                + Math.pow(Math.abs(y1 - y0), 2));

        double dL = (newLength / oldLength) - 1;

        image.setScaleX((float) (image.getScaleX() + dL));
        image.setScaleY((float) (image.getScaleY() + dL));

        oldLength = newLength;
    }

    private void RotateImage(float x0, float x1, float y0, float y1) {
        float xDiff = x1 - x0;
        float yDiff = y1 - y0;

        double newRotation = Math.toDegrees(Math.atan2(yDiff, xDiff));

        double rotationAngle = newRotation - oldRotation;

        image.setRotation(image.getRotation() + (float) rotationAngle);

        oldRotation = newRotation;
    }

    private View.OnClickListener saveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", image.getTranslationX());
            PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", image.getTranslationY());
            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", image.getScaleX());
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", image.getScaleY());
            PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat("rotation", image.getRotation());
            animations.add(ObjectAnimator.ofPropertyValuesHolder(image, translationX, translationY, scaleX, scaleY, rotation).setDuration(1500));

            Toast.makeText(FrameAnimator.this, "Frame saved!", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener playClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AnimatorSet as = new AnimatorSet();
            as.playSequentially(new ArrayList<Animator>(animations));
            as.start();
        }
    };
}
