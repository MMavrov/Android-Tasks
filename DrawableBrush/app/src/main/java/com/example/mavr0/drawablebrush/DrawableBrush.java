package com.example.mavr0.drawablebrush;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


public class DrawableBrush extends Activity {
    LinearLayout root;
    DrawableView drawableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_brush);

        root = (LinearLayout) findViewById(R.id.root);
        drawableView = new DrawableView(this);
        root.addView(drawableView);
    }

    public class DrawableView extends View{
        private Bitmap  mBitmap;
        private Canvas mCanvas;

        public DrawableView(Context context) {
            super(context);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            Drawable currentBrush = DrawableBrush.this.getResources().getDrawable(R.drawable.guitar_icon);
            currentBrush.setBounds(
                    (int)event.getX(),
                    (int)event.getY(),
                    (int)event.getX()+100,
                    (int)event.getY()+100);
            currentBrush.setAlpha(150);
            currentBrush.draw(mCanvas);

            invalidate();

            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }
}
