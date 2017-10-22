package com.example.nguyenhuuquan.calltosantaclaus;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class CallVideoActivity extends AppCompatActivity {

    CircleImageView circleImageViewDecline, circleImageViewAccpet;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_video);

        //            player = new MediaPlayer();
//            player.setDataSource(String.valueOf(R.raw.call_messenger));
//            player.prepare();
//            player.start();
        player = MediaPlayer.create(this, R.raw.call_messenger);
//            player.prepare();
        player.start();
        player.setLooping(true);

        circleImageViewAccpet = (CircleImageView) findViewById(R.id.circleAccept);
        circleImageViewDecline = (CircleImageView) findViewById(R.id.circleDecline);

        circleImageViewAccpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                Intent intent = new Intent(CallVideoActivity.this, CallingActivity.class);
                startActivity(intent);
            }
        });

        circleImageViewDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                finish();
            }
        });

    }
}
