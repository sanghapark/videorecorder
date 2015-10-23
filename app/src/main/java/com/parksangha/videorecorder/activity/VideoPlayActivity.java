package com.parksangha.videorecorder.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.parksangha.videorecorder.R;

import java.io.File;

public class VideoPlayActivity extends Activity {

    final private String mFilePath = "/sdcard/Android/data/com.parksangha.videorecorder/files/video_360x202.mp4";

    private VideoView vv1;
    private VideoView vv2;

    private boolean vv1Playing;
    private boolean vv2Playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        File file = new File(mFilePath);
        if (file.exists()) {

            vv1 = (VideoView) findViewById(R.id.video_view1);
            vv1.setVideoPath(mFilePath);
            vv1.setMediaController(new MediaController(this));

            vv2 = (VideoView) findViewById(R.id.video_view2);
            vv2.setVideoPath(mFilePath);
            vv2.setMediaController(new MediaController(this));


            vv1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (vv2Playing) {
                        vv2.pause();
                        vv2Playing = false;
                    }
                    if (!vv1Playing) {
                        vv1.start();
                        vv1Playing = true;
                    } else {
                        vv1.pause();
                    }
                    vv1.requestFocus();
                    return false;
                }
            });

            vv2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (vv1Playing) {
                        vv1.pause();
                        vv1Playing = false;
                    }

                    if (!vv2Playing) {
                        vv2.start();
                        vv2Playing = true;
                    } else {
                        vv2.pause();
                    }
                    vv2.requestFocus();
                    return false;
                }
            });


//        vv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(vv2Playing){
//                    vv2.pause();
//                }
//                if(!vv1Playing) {
//                    vv1.start();
//                    vv1Playing = true;
//                }
//                else{
//                    vv1.pause();
//                }
//                vv1.requestFocus();
//            }
//        });


//        vv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (vv1Playing){
//                    vv1.pause();
//                }
//
//                if(!vv2Playing) {
//                    vv2.start();
//                    vv2Playing = true;
//                }
//                else{
//                    vv2.pause();
//                }
//                vv2.requestFocus();
//            }
//        });
        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Notice");
            alertDialog.setMessage("Video does not exist.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            alertDialog.show();
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
