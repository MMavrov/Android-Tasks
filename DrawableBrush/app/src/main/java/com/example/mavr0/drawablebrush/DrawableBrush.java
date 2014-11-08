package com.example.mavr0.drawablebrush;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class DrawableBrush extends Activity {
    LinearLayout root;

    ImageButton guitarButton;
    ImageButton golangButton;
    ImageButton drumsButton;

    DrawableView drawableView;
    Drawable currentBrush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_brush);

        root = (LinearLayout) findViewById(R.id.root);
        drawableView = new DrawableView(this);
        root.addView(drawableView);

        guitarButton = (ImageButton) findViewById(R.id.guitar);
        guitarButton.setOnClickListener(brushChangedListener);
        guitarButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_green));

        golangButton = (ImageButton) findViewById(R.id.golang);
        golangButton.setOnClickListener(brushChangedListener);
        golangButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_red));

        drumsButton = (ImageButton) findViewById(R.id.drums);
        drumsButton.setOnClickListener(brushChangedListener);
        drumsButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_red));

        currentBrush = getResources().getDrawable(R.drawable.guitar_icon);
    }

    View.OnClickListener brushChangedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == guitarButton){
                currentBrush = getResources().getDrawable(R.drawable.guitar_icon);
                guitarButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_green));
                golangButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_red));
                drumsButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_red));
            }else if(v == golangButton) {
                currentBrush = getResources().getDrawable(R.drawable.golang);
                guitarButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_red));
                golangButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_green));
                drumsButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_red));
            }else {
                currentBrush = getResources().getDrawable(R.drawable.drums_icon);
                guitarButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_red));
                golangButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_red));
                drumsButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_green));
            }
        }
    };

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
            currentBrush.setBounds(
                    (int)event.getX(),
                    (int)event.getY(),
                    (int)event.getX()+100,
                    (int)event.getY()+100);
            // We call mutate() because otherwise we change the Drawable's property in the BrushesLayout
            currentBrush.mutate().setAlpha(150);
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
