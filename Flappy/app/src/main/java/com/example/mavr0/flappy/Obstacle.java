package com.example.mavr0.flappy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.widget.ImageView;

import java.util.Random;

public class Obstacle extends ImageView implements GameClock.GameClockListener {
    private Bitmap mBitmap;
    private Point screenSize;
    private int displacementX;
    private int obstacleCenterY;

    private final int OBSTACLE_WIDTH = 100;

    public Obstacle(Context context, Point screenSize) {
        super(context);

        this.screenSize = screenSize;

        displacementX = screenSize.x;
        Random r = new Random();
        obstacleCenterY = r.nextInt(screenSize.y - 400) + 200;

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bush);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawBitmap(mBitmap,
                null,
                new Rect(displacementX, 0, displacementX + OBSTACLE_WIDTH, obstacleCenterY - 100), null);

        canvas.drawBitmap(mBitmap,
                null,
                new Rect(displacementX, obstacleCenterY + 100, displacementX + OBSTACLE_WIDTH, screenSize.y), null);

        displacementX--;

        if(displacementX + OBSTACLE_WIDTH == 0) {
            drawNextObstacle();
        }
    }

    public Rect getPosition() {
        return new Rect(displacementX, obstacleCenterY - 100, displacementX + OBSTACLE_WIDTH, obstacleCenterY + 100);
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {

    }

    public void reset(){
        drawNextObstacle();
    }

    private void drawNextObstacle(){
        displacementX = screenSize.x;
        Random r = new Random();
        obstacleCenterY = r.nextInt(screenSize.y - 400) + 200;
    }
}
