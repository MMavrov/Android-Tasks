package com.example.mavr0.funwithflags;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;


public class Flager extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        runFlagsEasy();


    }

    private void runFlagsEasy() {
        setContentView(R.layout.flags_easy);

        View topHorizontal = findViewById(R.id.topHorizontal);
        View middleHorizontal = findViewById(R.id.middleHorizontal);
        View bottomHorizontal = findViewById(R.id.bottomHorizontal);

        View leftVertical = findViewById(R.id.leftVertical);
        View middleVertical = findViewById(R.id.middleVertical);
        View rightVertical = findViewById(R.id.rightVertical);

        final int[] colors = getResources().getIntArray(R.array.rainbow);

        topHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = new Random().nextInt(colors.length);
                v.setBackgroundColor(colors[randomNumber]);
            }
        });

        middleHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = new Random().nextInt(colors.length);
                v.setBackgroundColor(colors[randomNumber]);
            }
        });

        bottomHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = new Random().nextInt(colors.length);
                v.setBackgroundColor(colors[randomNumber]);
            }
        });

        leftVertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = new Random().nextInt(colors.length);
                v.setBackgroundColor(colors[randomNumber]);
            }
        });

        middleVertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = new Random().nextInt(colors.length);
                v.setBackgroundColor(colors[randomNumber]);
            }
        });

        rightVertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = new Random().nextInt(colors.length);
                v.setBackgroundColor(colors[randomNumber]);
            }
        });
    }
}
