package com.example.mavr0.swipeandzoom;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SwipeZoomActivity extends Activity {
    private ImageView imageView;
    private TextView counterView;
    private TypedArray imagesArray;
    private int currentImageNumber;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_zoom);
        currentImageNumber = 0;

        imagesArray = getResources().obtainTypedArray(R.array.images);

        imageView = (ImageView) findViewById(R.id.image_view);
        imageView.setImageDrawable(imagesArray.getDrawable(currentImageNumber));

        counterView = (TextView) findViewById(R.id.counter_view);
        counterView.setText(currentImageNumber + 1 + " / " + imagesArray.length());

        gestureDetector = new GestureDetector(this, simpleOnGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // The gestureDetector.onTouchEvent method shows a good implementation of the basic movement actions
        return gestureDetector.onTouchEvent(event);
    }

    GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("onFling", " onFling");

            int dx = (int) (e2.getX() - e1.getX());
            if (Math.abs(dx) > 30 && Math.abs(velocityX) > Math.abs(velocityY)) {
                if (velocityX > 0) {
                    Moveleft();
                } else {
                    MoveRight();
                }
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (imageView.getScaleX() == 2){
                imageView.setScaleX((float) 1);
                imageView.setScaleY((float) 1);

                counterView.setTranslationY(0);
            }else {
                imageView.setScaleX((float) 2);
                imageView.setScaleY((float) 2);

                counterView.setTranslationY(120);
            }
            return true;
        }
    };

    private void MoveRight() {
        if (currentImageNumber == imagesArray.length() - 1) {
            Toast.makeText(SwipeZoomActivity.this, "No more images!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            currentImageNumber += 1;

            imageView.setImageDrawable(imagesArray.getDrawable(currentImageNumber));
            imageView.invalidate();

            counterView.setText(currentImageNumber + 1 + " / " + imagesArray.length());
        }
    }

    private void Moveleft() {
        if (currentImageNumber == 0) {
            Toast.makeText(SwipeZoomActivity.this, "No more images!", Toast.LENGTH_LONG).show();
            return;
        } else {
            currentImageNumber -= 1;

            imageView.setImageDrawable(imagesArray.getDrawable(currentImageNumber));
            imageView.invalidate();

            counterView.setText(currentImageNumber + 1 + " / " + imagesArray.length());
        }
    }
}
