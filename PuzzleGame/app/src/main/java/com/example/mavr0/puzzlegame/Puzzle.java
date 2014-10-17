package com.example.mavr0.puzzlegame;

import android.app.Activity;
import android.content.ClipData;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
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
    ImageView[] imagePieces;
    RelativeLayout layout;
    Hashtable<Integer, Integer> linkTable;
    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        width = getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();

        DrawBoard();
    }

    private void DrawBoard()
    {
        layout = (RelativeLayout)findViewById(R.id.main);
        TypedArray imagesArray = getResources().obtainTypedArray(R.array.images);
        linkTable = new Hashtable<Integer, Integer>();
        imagePieces = new ImageView[imagesArray.length()];

        for(int i = 0; i < imagesArray.length(); ++i) {
            imagePieces[i] = new ImageView(this);

            imagePieces[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ClipData.Item item = new ClipData.Item((String.valueOf(v.getId())));

                    ClipData dragData = new ClipData(item.getText(), new String[]{}, item);

                    // Instantiates the drag shadow builder.
                    View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);

                    // Starts the drag
                    v.startDrag(dragData,  // the data to be dragged
                            myShadow,  // the drag shadow builder
                            null,      // here set the imagePieces[i] and get it in the DragListener with event.getLocalState()
                            0          // flags (not currently used, set to 0)
                    );

                    return true;
                }
            });

            imagePieces[i].setOnDragListener(new ImageView.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    if(event.getAction() == DragEvent.ACTION_DROP) {

                        ClipData.Item shadow = event.getClipData().getItemAt(0);
                        ImageView shadowView = (ImageView)findViewById(Integer.parseInt(shadow.getText().toString()));

                        Drawable target = ((ImageView)v).getDrawable();
                        ((ImageView)v).setImageDrawable(shadowView.getDrawable());
                        shadowView.setImageDrawable(target);

                        int x = linkTable.get(((ImageView) v).getId());
                        int y = linkTable.get(shadowView.getId());
                        linkTable.put(shadowView.getId(), x);
                        linkTable.put(((ImageView)v).getId(), y);

                        for (Integer i = 1; i <= 16; i++) {
                            if(linkTable.get(i) != i)
                            {
                                return true;
                            }
                        }

                        Toast.makeText(Puzzle.this, "YOU WIN!", Toast.LENGTH_LONG).show();

                    }
                    return true;
                }
            });

            // When programmatically creating views, set ID
            // ID cannot be 0
            imagePieces[i].setId(i+1);
            imagePieces[i].setPadding(1, 1, 1, 1);
            imagePieces[i].setAdjustViewBounds(false);
            imagePieces[i].setScaleType(ImageView.ScaleType.FIT_XY);

            // Shuffle of images
            int random = new Random().nextInt(16);
            while (linkTable.containsValue(random + 1)){
                random = new Random().nextInt(16);
            };
            linkTable.put(i+1, random+1);

            imagePieces[i].setImageDrawable(imagesArray.getDrawable(random));

            // Fixing the exact position and size
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width/4, height/4);
            if(i == 0)
            {
                layout.addView(imagePieces[i], params);
            }
            else if(i % 4 != 0)
            {
                params.addRule(RelativeLayout.RIGHT_OF, imagePieces[i-1].getId());
                params.addRule(RelativeLayout.ALIGN_BOTTOM, imagePieces[i-1].getId());
                layout.addView(imagePieces[i], params);
            }
            else
            {
                params.addRule(RelativeLayout.BELOW, imagePieces[i-4].getId());
                layout.addView(imagePieces[i], params);
            }
        }

        imagesArray.recycle();
    }


}
