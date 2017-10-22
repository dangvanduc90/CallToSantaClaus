package com.example.nguyenhuuquan.calltosantaclaus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButton;
    VideoView videoView;
//    String videoString = "android.resource://"+getApplicationContext().getPackageName()+"/raw/v4618";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String videoString = "android.resource://"+getApplicationContext().getPackageName()+"/raw/v4618";

        initView();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CallVideoActivity.class);
                startActivity(intent);
            }
        });
        videoView.setVideoURI(Uri.parse(videoString));
        videoView.setMediaController(new MediaController(MainActivity.this));
        videoView.requestFocus();
        videoView.setMediaController(null);
        videoView.start();
    }

    private void initView() {
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        videoView = (VideoView) findViewById(R.id.videoView);
    }
}
