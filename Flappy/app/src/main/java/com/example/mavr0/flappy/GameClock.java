package com.example.mavr0.flappy;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class GameClock {
    private List<GameClockListener> clockListeners = new ArrayList<GameClockListener>();

    public static interface GameClockListener {
        public void onGameEvent(GameEvent gameEvent);
    }

    private Handler handler = new Handler();

    public GameClock() {
        handler.post(new ClockRunnable());
    }

    public void subscribe(GameClockListener listener) {
        clockListeners.add(listener);
    }

    private class ClockRunnable implements Runnable {
        @Override
        public void run() {
            onTimerTick();
            handler.postDelayed(this, 16);
        }

        private void onTimerTick() {
            for (GameClockListener listener : clockListeners) {
                listener.onGameEvent(new GameEvent());
            }
        }
    }
}
