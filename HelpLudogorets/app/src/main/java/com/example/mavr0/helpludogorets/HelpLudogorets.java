package com.example.mavr0.helpludogorets;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;


public class HelpLudogorets extends Activity {
    VideoView video;
    Button play_pause;
    Button forward;
    Button rewind;
    boolean play_pause_flag;
    int timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_ludogorets);

        video = (VideoView) findViewById(R.id.video);
        play_pause = (Button) findViewById(R.id.play_pause);
        forward = (Button) findViewById(R.id.fwd);
        rewind = (Button) findViewById(R.id.rew);

        File videoFile = new File(Environment.getExternalStorageDirectory(), "NoPenalty.mp4");
        video.setVideoURI(Uri.fromFile(videoFile));

        play_pause_flag = true;
        timestamp = 0;

        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play_pause_flag) {
                    Play();
                } else {
                    Pause();
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resume(+3000);
            }
        });

        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resume(-3000);
            }
        });
    }

    private void Play(){
        video.seekTo(timestamp);
        video.start();
        play_pause.setBackgroundDrawable(getResources().getDrawable(R.drawable.pause)); //Using this method because of my device's old version
        play_pause_flag = false;
    }

    private void Pause(){
        video.pause();
        play_pause.setBackgroundDrawable(getResources().getDrawable(R.drawable.play)); //Using this method because of my device's old version
        play_pause_flag = true;

        timestamp = video.getCurrentPosition();
    }

    private void Resume(int seconds){
        video.pause();
        play_pause.setBackgroundDrawable(getResources().getDrawable(R.drawable.play)); //Using this method because of my device's old version
        play_pause_flag = true;

        video.seekTo(video.getCurrentPosition() + seconds);
        timestamp = video.getCurrentPosition();

    }
}
