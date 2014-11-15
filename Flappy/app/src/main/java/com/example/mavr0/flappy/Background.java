package com.example.mavr0.flappy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.widget.ImageView;

public class Background extends ImageView implements GameClock.GameClockListener {
    private Bitmap mBitmap;
    private Canvas mCanvas;

    public Background(Context context) {
        super(context);
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public PointF getPosition(){
        PointF position = new PointF();
        int[] coordinates = new int[2];
        getLocationOnScreen(coordinates);

        position.set(coordinates[0], coordinates[1]);
        return position;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
