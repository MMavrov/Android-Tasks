package com.example.mavr0.colorprevewer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class ColorPreviewer extends Activity {
    EditText color;
    View canvas;
    Button showColorButton;
    InputMethodManager imm;
    CheckBox autoDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_previewer);

        color = (EditText)findViewById(R.id.color);
        autoDraw = (CheckBox)findViewById(R.id.checkbox);
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

        color.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(color.getText().toString().length() == 7 && autoDraw.isChecked()){
                    canvas.setBackgroundColor((Color.parseColor(s.toString())));
                }
            }
        });
    }
}
