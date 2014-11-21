package com.example.mavr0.flappy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class DrawingView extends View implements GameClock.GameClockListener{

    private Canvas mCanvas;
    private Background background;
    private Bird bird;

    public DrawingView(Context context, GameClock gameClock) {
        super(context);

        Point screenSize = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(screenSize);

//        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.prey_overture);
//        mediaPlayer.setLooping(true);
//        mediaPlayer.start();

        mCanvas = new Canvas();
        background = new Background(context, screenSize);
        bird = new Bird(context, screenSize);

        gameClock.subscribe(background);
        gameClock.subscribe(bird);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        background.draw(canvas);
        bird.draw(canvas);
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Point birdPosition = bird.getPosition();
        bird.setPosition(new Point(birdPosition.x, birdPosition.y - 50));

        return super.onTouchEvent(event);
    }
}
