package com.example.mavr0.puzzlegame;

import android.app.Activity;
import android.content.ClipData;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Hashtable;
import java.util.Random;

public class Puzzle extends Activity {
    ImageView[] imagePieces;
    RelativeLayout layout;
    Hashtable<Integer, Integer> linkTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

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

            imagePieces[i].setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipData.Item item = new ClipData.Item((String.valueOf(v.getId())));

                    ClipData dragData = new ClipData(item.getText(), new String[]{}, item);

                    // Instantiates the drag shadow builder.
                    View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);

                    // Starts the drag
                    v.startDrag(dragData,  // the data to be dragged
                            myShadow,  // the drag shadow builder
                            null,      // no need to use local data
                            0          // flags (not currently used, set to 0)
                    );

                    return true;
                }
            });

            imagePieces[i].setOnDragListener(new ImageView.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    switch (event.getAction()) {

                        case DragEvent.ACTION_DRAG_STARTED:
                            return true;
                        case DragEvent.ACTION_DRAG_ENTERED:
                            return true;
                        case DragEvent.ACTION_DRAG_LOCATION:
                            return true;
                        case DragEvent.ACTION_DRAG_EXITED:
                            return true;
                        case DragEvent.ACTION_DROP: {
                            ClipData.Item target = event.getClipData().getItemAt(0);
                            ImageView f = (ImageView)findViewById(Integer.parseInt(target.getText().toString()));
                            Drawable t = ((ImageView)v).getDrawable();

                            ((ImageView)v).setImageDrawable(f.getDrawable());

                            f.setImageDrawable(t);

                            v.invalidate();

                            return true;
                        }

                        case DragEvent.ACTION_DRAG_ENDED:
                            return true;

                    }
                    return false;
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
            while (linkTable.containsValue(random)){
                random = new Random().nextInt(16);
            };
            linkTable.put(i, random);

            imagePieces[i].setImageDrawable(imagesArray.getDrawable(random));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 120);
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
