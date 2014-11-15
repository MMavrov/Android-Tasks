package com.example.mavr0.flappy;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;


public class FlappyMainActivity extends Activity {
    GameClock gameClock = new GameClock();

    Bird bird;
    Background background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawingView drawingView = new DrawingView(this);

        setContentView(drawingView);

        bird = new Bird(getApplicationContext());
        bird.setImageDrawable(getResources().getDrawable(R.drawable.bird));
        bird.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT));

        background = new Background(getApplicationContext());
        background.setImageDrawable(getResources().getDrawable(R.drawable.clouds));

        gameClock.subscribe(bird);
        gameClock.subscribe(background);
        gameClock.subscribe(drawingView);
    }

    private class DrawingView extends ImageView implements GameClock.GameClockListener {
        private Bitmap  mBitmap;
        private Canvas mCanvas;

        public DrawingView(Context context) {
            super(context);

            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.prey_overture);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawBitmap(mBitmap, 0, 0, null);
        }

        @Override
        public void onGameEvent(GameEvent gameEvent) {
            invalidate();
        }
    }
}