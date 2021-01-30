package com.skilbox.elvis;

        import androidx.appcompat.app.AppCompatActivity;
        import android.media.MediaPlayer;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;
        import java.lang.reflect.Field;
        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView name;
    MediaPlayer mediaPlayer;
    ArrayList<String> songs = new ArrayList<String>();
    int treckNum = 0;
    boolean startClick = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.textView2);

        Field[] fields = R.raw.class.getFields();
        for (Field f: fields){
            songs.add(f.getName());
        }

        mediaPlayer = MediaPlayer.create(this, Uri.parse("android.resource://com.skilbox.elvis/raw/" + songs.get(treckNum)));

    }

    public void onClickPause(View view) {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }

    }

    public void onClickStart(View view)
    {
        mediaPlayer.start();
        name.setText(songs.get(treckNum));
    }

    public void onClickNext(View view) {

        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this, Uri.parse("android.resource://com.skilbox.elvis/raw/" + songs.get(playingNowIndex())));
        name.setText(songs.get(treckNum));
        mediaPlayer.start();

    }

    public int playingNowIndex(){

        if (treckNum + 1 >= songs.size())
        {
            treckNum = 0;
        }else
        {
            treckNum = treckNum + 1;
        }

        return treckNum;
    }
}
