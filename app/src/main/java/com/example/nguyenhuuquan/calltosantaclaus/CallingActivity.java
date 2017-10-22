package com.example.nguyenhuuquan.calltosantaclaus;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class CallingActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    CircleImageView circleMute, circleEnd, circleVideo;
    VideoView videoView;
    final static int CAMERA_PERMISSION_REQUEST_CODE = 1;

    Camera camera;
    SurfaceView camView;
    SurfaceHolder surfaceHolder;
    boolean camCondition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        initView();
        camView.setZOrderOnTop(true);

        String videoString = "android.resource://"+getApplicationContext().getPackageName()+"/raw/v4618";
        videoView.setVideoURI(Uri.parse(videoString));
        videoView.setMediaController(new MediaController(CallingActivity.this));
        videoView.requestFocus();
        videoView.setMediaController(null);
        videoView.start();

        circleEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        circleMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoView.isPlaying()) {

                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.CAMERA}, 1);
            }
        }

        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceHolder = camView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(PixelFormat.OPAQUE);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
    }

    private void initView() {
        videoView = (VideoView) findViewById(R.id.videoView);
        circleMute = (CircleImageView) findViewById(R.id.circleMute);
        circleEnd = (CircleImageView) findViewById(R.id.circleEnd);
        circleVideo = (CircleImageView) findViewById(R.id.circleVideo);
        camView = (SurfaceView) findViewById(R.id.camerapreview);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Start your camera handling here
                } else {
                    Toast.makeText(CallingActivity.this, "decline", Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            camera = Camera.open(1);
            camera.setDisplayOrientation(90);
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
            camCondition = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera = null;
        camCondition = false;
    }
}
