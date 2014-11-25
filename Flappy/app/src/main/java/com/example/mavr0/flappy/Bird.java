package com.example.mavr0.flappy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.ImageView;

public class Bird extends ImageView implements GameClock.GameClockListener {
    private Bitmap mBitmap;

    private Point currentPosition;
    private Point startPosition = new Point();

    public Bird(Context context, Point screenSize) {
        super(context);

        startPosition.set(60, screenSize.y / 2);

        currentPosition = new Point(startPosition);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawBitmap(mBitmap, currentPosition.x, currentPosition.y, null);
        currentPosition.y++;
    }

    public Rect getCurrentPosition() {
        return new Rect(currentPosition.x, currentPosition.y, currentPosition.x + mBitmap.getWidth(), currentPosition.y + mBitmap.getHeight());
    }

    public void setCurrentPosition(Rect currentPosition){
        this.currentPosition.y = currentPosition.top;
    }

    public void reset(){
        currentPosition = new Point(startPosition);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
