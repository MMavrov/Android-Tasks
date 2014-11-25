package com.example.mavr0.flappy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class DrawingView extends View implements GameClock.GameClockListener{

    private Canvas mCanvas;
    private Background background;
    private Bird bird;
    private Obstacle obstacle;
    private Context context;
    private Point screenSize;

    public DrawingView(Context context, GameClock gameClock) {
        super(context);

        this.context = context;

        screenSize = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(screenSize);

//        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.prey_overture);
//        mediaPlayer.setLooping(true);
//        mediaPlayer.start();

        mCanvas = new Canvas();
        background = new Background(context, screenSize);
        bird = new Bird(context, screenSize);
        obstacle = new Obstacle(context, screenSize);

        gameClock.subscribe(background);
        gameClock.subscribe(bird);
        gameClock.subscribe(obstacle);
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
        obstacle.draw(canvas);

        detectCollision();
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Rect birdPosition = bird.getCurrentPosition();
        bird.setCurrentPosition(new Rect(birdPosition.left, birdPosition.top - 50, birdPosition.right, birdPosition.bottom - 50));

        return super.onTouchEvent(event);
    }

    private void detectCollision(){
        if (bird.getCurrentPosition().left > obstacle.getPosition().right)
            return;
        else if((bird.getCurrentPosition().right >= obstacle.getPosition().left
                    && (bird.getCurrentPosition().top <= obstacle.getPosition().top
                        || bird.getCurrentPosition().bottom >= obstacle.getPosition().bottom))
                || bird.getCurrentPosition().top <= 0
                || bird.getCurrentPosition().bottom >= screenSize.y){
            Toast.makeText(context, "YOU LOSE", Toast.LENGTH_SHORT).show();

            bird.reset();
            obstacle.reset();
        }
    }
}
