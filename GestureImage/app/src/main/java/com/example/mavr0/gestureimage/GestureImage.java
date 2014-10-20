package com.example.mavr0.gestureimage;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GestureImage extends Activity {
    private List<Integer> pointerIDs;
    private int _xDelta;
    private int _yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_image);

        pointerIDs = new ArrayList<Integer>();
        final ImageView gunman = (ImageView) findViewById(R.id.gunman);

        gunman.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {

                    case (MotionEvent.ACTION_DOWN) :
                        _xDelta = (int) (event.getRawX() - v.getX());
                        _yDelta = (int) (event.getRawY() - v.getY());

                        break;

                    case (MotionEvent.ACTION_MOVE) :
                        v.setTranslationX(event.getRawX() - _xDelta);
                        v.setTranslationY(event.getRawY() - _yDelta);
                        break;
                }

                return true;
            }
        });
    }
}
