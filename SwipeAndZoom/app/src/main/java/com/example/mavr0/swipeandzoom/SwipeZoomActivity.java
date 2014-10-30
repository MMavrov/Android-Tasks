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

public class SwipeZoomActivity extends Activity implements GestureDetector.OnGestureListener  {
    private ImageView imageView;
    private TextView currentNumberView;
    private TypedArray imagesArray;
    private int currentImageNumber;

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_zoom);

        Initialize();
    }

    private void Initialize() {

        imageView = (ImageView) findViewById(R.id.image_view);

        currentNumberView = (TextView) findViewById(R.id.current_number_view);
        currentImageNumber = 0;
        imagesArray = getResources().obtainTypedArray(R.array.images);

        imageView.setImageDrawable(imagesArray.getDrawable(currentImageNumber));

        currentNumberView.setText(currentImageNumber + 1 + " / " + imagesArray.length());

        gestureDetector = new GestureDetector(this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("onFling", " onFling");

        int dx = (int) (e2.getX() - e1.getX());
        // don't accept the fling if it's too short
        // as it may conflict with a button push
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

    private void MoveRight() {
        if (currentImageNumber == imagesArray.length() - 1) {
            Toast.makeText(SwipeZoomActivity.this, "No more images!", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            currentImageNumber += 1;

            imageView.setImageDrawable(imagesArray.getDrawable(currentImageNumber));
            imageView.invalidate();

            currentNumberView.setText(currentImageNumber + 1 + " / " + imagesArray.length());
        }
    }

    private void Moveleft() {
        if (currentImageNumber == 0) {
            Toast.makeText(SwipeZoomActivity.this, "No more images!", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            currentImageNumber -= 1;

            imageView.setImageDrawable(imagesArray.getDrawable(currentImageNumber));
            imageView.invalidate();

            currentNumberView.setText(currentImageNumber + 1 + " / " + imagesArray.length());
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("onDown", " onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("onShowPress", " onShowPress");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("onSingleTapUp", " onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("onScroll", " onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("onLongPress", " onLongPress");

    }
}
