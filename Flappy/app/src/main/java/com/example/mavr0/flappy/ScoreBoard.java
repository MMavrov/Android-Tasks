package com.example.mavr0.flappy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.TextView;

public class ScoreBoard extends TextView implements GameClock.GameClockListener {
    private long mStartTime;

    public ScoreBoard(Context context) {
        super(context);

        mStartTime = System.currentTimeMillis();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY);
        paint.setAntiAlias(true);
        paint.setTextSize(150);
        paint.setAlpha(100);
        canvas.drawText(String.format("%d", getScore()), 50, 150, paint);
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {

    }

    public long getScore(){
        return (long)((System.currentTimeMillis() - mStartTime) * 0.001);
    }

    public void reset() {
        mStartTime = System.currentTimeMillis();
    }
}
