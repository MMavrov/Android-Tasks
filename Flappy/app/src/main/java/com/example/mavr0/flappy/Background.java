package com.example.mavr0.flappy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

public class Background extends ImageView implements GameClock.GameClockListener {
    private Bitmap mBitmap;
    private int currentPositionX = 0;

    public Background(Context context, Point screenSize) {
        super(context);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.clouds);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, screenSize.x + 400, screenSize.y, true)
                .copy(mBitmap.getConfig(), true);
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawBitmap(mBitmap, currentPositionX--, 0, null);
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
