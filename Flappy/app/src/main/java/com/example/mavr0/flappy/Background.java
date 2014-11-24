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
    private Point screenSize;

    public Background(Context context, Point screenSize) {
        super(context);

        this.screenSize = screenSize;

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

        if(Math.abs(currentPositionX) > mBitmap.getWidth() - screenSize.x){
            int difference = currentPositionX - (mBitmap.getWidth() - screenSize.x);

            canvas.drawBitmap(mBitmap,
                    new Rect(currentPositionX, 0, screenSize.x + currentPositionX, screenSize.y),
                    new Rect(0, 0, screenSize.x - difference, screenSize.y), null);

            canvas.drawBitmap(mBitmap,
                    new Rect(0, 0, difference, screenSize.y),
                    new Rect(screenSize.x - difference, 0, screenSize.x, screenSize.y), null);

            currentPositionX++;
            if(currentPositionX == mBitmap.getWidth()){
                currentPositionX = 0;
            }
        } else {
            canvas.drawBitmap(mBitmap,
                    new Rect(currentPositionX, 0, screenSize.x + currentPositionX, screenSize.y),
                    new Rect(0, 0, screenSize.x, screenSize.y), null);

            currentPositionX++;
        }

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
