package com.example.mavr0.flappy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.ImageView;

public class Bird extends ImageView implements GameClock.GameClockListener {
    private Bitmap mBitmap;

    private Point position = new Point();

    public Bird(Context context, Point screenSize) {
        super(context);

        position.set(60, screenSize.y / 2);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawBitmap(mBitmap, position.x, position.y, null);
        position.y++;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position){
        this.position.y = position.y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
