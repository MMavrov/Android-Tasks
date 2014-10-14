package com.example.mavr0.colorprevewer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


public class ColorPreviewer extends Activity {
    EditText color;
    View canvas;
    Button showColorButton;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_previewer);

        color = (EditText)findViewById(R.id.color);

        canvas = findViewById(R.id.canvas);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        showColorButton = (Button)findViewById(R.id.show_color_button);
        showColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setBackgroundColor((Color.parseColor(color.getText().toString())));
                imm.hideSoftInputFromWindow(color.getWindowToken(), 0);
            }
        });
    }
}
