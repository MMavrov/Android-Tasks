package com.example.mavr0.flappy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;

public class DrawingView extends View implements GameClock.GameClockListener{

    private Canvas mCanvas;
    private Background background;

    public DrawingView(Context context, GameClock gameClock) {
        super(context);

//        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.prey_overture);
//        mediaPlayer.setLooping(true);
//        mediaPlayer.start();

        mCanvas = new Canvas();
        background = new Background(context);

        gameClock.subscribe(background);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        background.draw(canvas);
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {
        invalidate();
    }
}
