package com.skilbox.myvideopleyer;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    int intPosition = 0;
    boolean pleying;
    boolean wasPleying ;

    public static final String TAG ="MyMessage";
    public static final String intPositionKey = "intPosition";
    public static final String pleyingKey = "pleying";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);

        videoView.setMediaController(new MediaController(this));
        String pathToVideoFile =  "android.resource://"+getPackageName()+"/"+R.raw.videoplayback;
        videoView.setVideoPath(pathToVideoFile);

        if (savedInstanceState != null)
        {
            intPosition = savedInstanceState.getInt(intPositionKey);
            pleying = savedInstanceState.getBoolean(pleyingKey);
            videoView.seekTo(intPosition);
            videoView.start();
        }

        // Print Log
        Log.i(TAG,"onCreate  " + intPosition);

        onPley();
    }

    private void onPley() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                if (pleying)
                {
                    Log.i(TAG,"onPley  " + intPosition);
                    videoView.seekTo(intPosition);
                    videoView.start();
                }
            }
    });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanse1 " + intPosition);
        outState.putInt(intPositionKey,intPosition);//сохраняем значение переменной
        outState.putBoolean(pleyingKey,pleying);
        Log.i(TAG,"onSaveInstanse 2" + intPosition);
    }


    @Override
    public void onPause(){
        super.onPause();
        intPosition = videoView.getCurrentPosition();//получаем позицию и сохраняем в переменную
        wasPleying = pleying;
        pleying = false;
        // Print Log
        Log.i(TAG,"onPause " + intPosition);

    }


    @Override
    protected void onStop() {
        super.onStop();
        wasPleying = pleying;
        pleying = false;

        // Print Log
        Log.i(TAG,"onStop " + intPosition);
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoView.seekTo(intPosition);
        videoView.start();

        if (wasPleying){
            pleying = true;
        }

        // Print Log
        Log.i(TAG,"onStart " + intPosition);
    }
//
    @Override
    public void onResume(){
        super.onResume();

        if (wasPleying){
            pleying = true;
        }

        // Print Log
        Log.i(TAG,"onResume " + intPosition);
    }

}
