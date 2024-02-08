package com.naqb.videoplayer;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.idVideoView);

        // Load video from raw resources
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.sinarpelangi;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(mp -> videoView.setBackgroundResource(R.drawable.play));
        videoView.setOnCompletionListener(mp -> videoView.setBackground(null));

        videoView.setOnTouchListener((v, event) -> {
            if (videoView.isPlaying()) {
                videoView.pause();
                videoView.setBackgroundResource(R.drawable.play);
            } else {
                videoView.start();
                videoView.setBackground(null);
            }
            return false;
        });
    }
}