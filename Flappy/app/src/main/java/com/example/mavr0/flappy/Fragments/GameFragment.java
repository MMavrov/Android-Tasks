package com.example.mavr0.flappy.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mavr0.flappy.DrawingView;
import com.example.mavr0.flappy.GameClock;
import com.example.mavr0.flappy.R;

public class GameFragment extends Fragment {
    GameClock gameClock = new GameClock();

    public GameFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DrawingView drawingView = new DrawingView(getActivity().getApplicationContext(), gameClock);
        getActivity().setContentView(drawingView);

        gameClock.subscribe(drawingView);

        return inflater.inflate(R.layout.fragment_game, container, false);
    }
}
