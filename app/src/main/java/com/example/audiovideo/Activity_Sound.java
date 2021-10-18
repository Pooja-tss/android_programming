package com.example.audiovideo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.*;

public class Activity_Sound extends Activity implements Runnable {

    private Button playBtn;
    private Button stopBtn;
    private Button pauseBtn;
    private SeekBar seekBar;
    private MediaPlayer soundPlayer;
    private Thread soundThread;
    private Button videoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.playBtn);
        stopBtn = findViewById(R.id.stopBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        seekBar = findViewById(R.id.seekBar);
        soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.alone);
        videoBtn = findViewById(R.id.videoBtn);
//        videoButton = findViewById(R.id.videoView);

        setupListeners();

        soundThread = new Thread((Runnable) this);
        soundThread.start();
    }

    private void setupListeners() {

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPlayer.start();
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPlayer.pause();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View currentView) {
                soundPlayer.stop();
                soundPlayer = MediaPlayer.create(getBaseContext(),R.raw.alone);
            }
        });

        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View currentView) {
                Intent myIntent = new Intent(currentView.getContext(), Activity_Video.class);
                startActivityForResult(myIntent,0);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b)
                {
                    soundPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void run()
    {
        int currentPosition = 0;
        int soundTotal = soundPlayer.getDuration();
        seekBar.setMax(soundTotal);

        while(soundPlayer != null && currentPosition < soundTotal)
        {
            try{
                Thread.sleep(300);
                currentPosition = soundPlayer.getCurrentPosition();
            }
            catch (InterruptedException soundException)
            {
                return;
            }
            catch (Exception otherException)
            {
                return;
            }
            seekBar.setProgress(currentPosition);
        }
    }
}