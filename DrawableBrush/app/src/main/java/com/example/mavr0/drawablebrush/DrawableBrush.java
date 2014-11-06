package com.example.mavr0.drawablebrush;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class DrawableBrush extends Activity {
    Drawable currentBrush;
    RelativeLayout root;
    LinearLayout brushesLayout;
    Brush view;
    Bitmap bitmap;
    Paint paint;
    Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_brush);

        root = (RelativeLayout) findViewById(R.id.root);
        brushesLayout = (LinearLayout) findViewById(R.id.brushes);

        bitmap = Bitmap.createBitmap(128, 128, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);

        view = new Brush(DrawableBrush.this);
        root.addView(view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        currentBrush = ((ImageButton) findViewById(R.id.guitar)).getDrawable();

        currentBrush.draw(canvas);

//        view.setLayoutParams(new RelativeLayout.LayoutParams(brushesLayout.getHeight(), brushesLayout.getWidth()/3));
        view.setX(event.getRawX());
        view.setY(event.getRawY());
        view.setAlpha(0.5f);
        view.invalidate();

        return true;
    }

    private class Brush extends View{

        public Brush(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas c) {
            c.drawBitmap(bitmap, 0, 0, null);
            super.onDraw(canvas);
        }
    }
}
