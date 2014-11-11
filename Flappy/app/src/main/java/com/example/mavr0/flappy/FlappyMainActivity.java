package com.example.mavr0.flappy;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;


public class FlappyMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawingView drawingView = new DrawingView(getApplicationContext());

        setContentView(drawingView);
    }

    private class DrawingView extends ImageView {

        public DrawingView(Context context) {
            super(context);

            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.prey_overture);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
        }
    }
}
