package org.mdsd2017.android.androidmp3playerdemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG= "In-MainActivity";
    Boolean isPlaying = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(this.TAG, "On Create");

        final Button playPauseBtn = (Button) this.findViewById(R.id.play_pause_btn);
        playPauseBtn.setOnClickListener(this);

        final Button stopBtn = (Button) this.findViewById(R.id.stop_btn);
        stopBtn.setOnClickListener(this);

        final Button prevBtn = (Button) this.findViewById(R.id.prev_btn);
        prevBtn.setOnClickListener(this);

        final Button nextBtn = (Button) this.findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e(this.TAG, "onDestroy");

        Intent intent = new Intent(this, BackgroundPlayBack.class);
        stopService(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.e(this.TAG, "onConfigurationChanged");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.e(this.TAG, "onRestoreInstanceState");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        Log.e(this.TAG, "onSaveInstanceState");
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch(v.getId()) {
            case R.id.play_pause_btn:
                intent = new Intent(this, BackgroundPlayBack.class);
                Log.e(this.TAG, "Button PLAY Clicked");
                if (isPlaying == null) {
                    intent.putExtra(BackgroundPlayBack.playerAction, BackgroundPlayBack.ACTION_PLAY);
                    intent.putExtra(BackgroundPlayBack.songToPlay, R.raw.bensoundindia);
                    isPlaying = true;
                    Log.e(this.TAG, "ACTION_PLAY");
                } else if(isPlaying) {
                    intent.putExtra(BackgroundPlayBack.playerAction, BackgroundPlayBack.ACTION_PAUSE);
                    isPlaying = false;
                    Log.e(this.TAG, "ACTION_PAUSE");
                } else {
                    intent.putExtra(BackgroundPlayBack.playerAction, BackgroundPlayBack.ACTION_RESUME);
                    isPlaying = true;
                    Log.e(this.TAG, "ACTION_RESUME");
                }
                startService(intent);
                break;
            case R.id.stop_btn:
                Log.e(this.TAG, "Button STOP Clicked");
                intent = new Intent(this, BackgroundPlayBack.class);
                isPlaying = null;
                stopService(intent);
                break;
            case R.id.prev_btn:
                Log.e(this.TAG, "Button PREV Clicked");
                break;
            case R.id.next_btn:
                Log.e(this.TAG, "Button NEXT Clicked");
                break;
            default:
                Log.e(this.TAG, "Button unknown Clicked");
                break;
        }

    }
}
