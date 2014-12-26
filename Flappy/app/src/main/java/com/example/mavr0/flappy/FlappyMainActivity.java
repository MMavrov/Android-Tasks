package com.example.mavr0.flappy;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.mavr0.flappy.Fragments.LoginFragment;


public class FlappyMainActivity extends Activity {
    GameClock gameClock = new GameClock();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.login_fragment, loginFragment).commit();

        setContentView(R.layout.fragment_login);

        DrawingView drawingView = new DrawingView(this, gameClock);
        gameClock.subscribe(drawingView);
    }
}