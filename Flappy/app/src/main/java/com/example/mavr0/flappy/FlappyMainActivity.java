package com.example.mavr0.flappy;

import android.app.Activity;
import android.os.Bundle;


public class FlappyMainActivity extends Activity {
    GameClock gameClock = new GameClock();

    Bird bird;
    Background background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawingView drawingView = new DrawingView(this, gameClock);

        setContentView(drawingView);

        gameClock.subscribe(drawingView);
    }
}