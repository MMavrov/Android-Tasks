package com.example.mavr0.puzzlegame;

import android.app.Activity;
import android.content.ClipData;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Hashtable;
import java.util.Random;

public class Puzzle extends Activity {
    private ImageView[] imageViewArray;
    private RelativeLayout rootLayout;
    private Hashtable<Integer, Integer> viewPositionTable;
    private int screenWidth, screenHeight;
    private TypedArray imagesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();

        rootLayout = (RelativeLayout) findViewById(R.id.main);
        imagesArray = getResources().obtainTypedArray(R.array.images);
        viewPositionTable = new Hashtable<Integer, Integer>();
        imageViewArray = new ImageView[imagesArray.length()];

        for (int i = 0; i < imagesArray.length(); ++i) {
            imageViewArray[i] = new ImageView(this);
            imageViewArray[i].setOnTouchListener(touchListener);
            imageViewArray[i].setOnDragListener(dragListener);

            SetupViewWithIndex(i);
            ArrangeViewWithIndex(i);
        }

        imagesArray.recycle();
    }

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ClipData.Item item = new ClipData.Item((String.valueOf(v.getId())));

            ClipData dragData = new ClipData(item.getText(), new String[]{}, item);

            // Instantiates the drag shadow builder.
            View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);

            // Starts the drag
            v.startDrag(dragData,  // the data to be dragged
                    myShadow,  // the drag shadow builder
                    null,      // here set the imageViewArray[i] and get it in the DragListener with event.getLocalState()
                    0          // flags (not currently used, set to 0)
            );

            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            if (event.getAction() == DragEvent.ACTION_DROP) {

                ClipData.Item shadow = event.getClipData().getItemAt(0);
                ImageView shadowView = (ImageView) findViewById(Integer.parseInt(shadow.getText().toString()));

                ImageView targetView = (ImageView) v;

                int targetViewPosition = viewPositionTable.get(targetView.getId());
                int shadowViewPosition = viewPositionTable.get(shadowView.getId());
                viewPositionTable.put(shadowView.getId(), targetViewPosition);
                viewPositionTable.put(targetView.getId(), shadowViewPosition);

                float oldX = (shadowView.getX());
                float oldY = (shadowView.getY());

                shadowView.animate()
                        .x(targetView.getX())
                        .y(targetView.getY())
                        .setDuration(500)
                        .start();

                targetView.animate()
                        .x(oldX)
                        .y(oldY)
                        .setDuration(500)
                        .start();

                if (WinCheck()) {
                    Toast.makeText(Puzzle.this, "YOU WIN!", Toast.LENGTH_LONG).show();
                }
            }
            return true;
        }
    };

    private void SetupViewWithIndex(int index){
        int random = new Random().nextInt(16);
        while (viewPositionTable.containsKey(random + 1)) {
            random = new Random().nextInt(16);
        }
        // Id cannot be 0
        // Each view's Id is actually the number of the corresponding image
        imageViewArray[index].setId(random + 1);
        // Says that view with Id=random+1 is found on index+1-th position in the grid
        viewPositionTable.put(random + 1, index + 1);

        imageViewArray[index].setImageDrawable(imagesArray.getDrawable(random));
    }

    private void ArrangeViewWithIndex(int index) {
        imageViewArray[index].setPadding(1, 1, 1, 1);
        imageViewArray[index].setAdjustViewBounds(false);
        imageViewArray[index].setScaleType(ImageView.ScaleType.FIT_XY);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(screenWidth / 4, screenHeight / 4);
        if (index == 0) {
            rootLayout.addView(imageViewArray[index], params);
        } else if (index % 4 != 0) {
            params.addRule(RelativeLayout.RIGHT_OF, imageViewArray[index - 1].getId());
            params.addRule(RelativeLayout.ALIGN_BOTTOM, imageViewArray[index - 1].getId());
            rootLayout.addView(imageViewArray[index], params);
        } else {
            params.addRule(RelativeLayout.BELOW, imageViewArray[index - 4].getId());
            rootLayout.addView(imageViewArray[index], params);
        }
    }

    private boolean WinCheck() {
        for (Integer i = 1; i <= 16; i++) {
            if (viewPositionTable.get(i) != i) {
                return false;
            }
        }
        return true;
    }
}
