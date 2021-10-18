package com.example.audiovideo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class Activity_Video extends Activity {

    private VideoView mPlayer;
    private Button returnButton;
    private MediaController myVideoController;
    private Uri videoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mPlayer = (VideoView) findViewById(R.id.videoView);
        returnButton = (Button) findViewById(R.id.homeButton);

        videoLocation = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.earthvideo);
        myVideoController = new MediaController(this);

        setupMedia();
        setupListeners();
    }

    private void setupListeners() {
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View currentView) {
                Intent myIntent = new Intent();
                setResult(RESULT_OK);
                finish();

            }
        });
    }

    private void setupMedia() {
        mPlayer.setMediaController(myVideoController);
        mPlayer.setVideoURI(videoLocation);
    }


}