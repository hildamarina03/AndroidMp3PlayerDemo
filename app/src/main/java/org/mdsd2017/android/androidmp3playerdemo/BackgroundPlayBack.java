package org.mdsd2017.android.androidmp3playerdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class BackgroundPlayBack extends Service  {

    private static final String TAG= "In-BackgroundPlayBack";
    public static final String playerAction= "playerAction";
    public static final String songToPlay= "song";
    public static final int ACTION_PLAY= 0;
    public static final int ACTION_PAUSE= 1;
    public static final int ACTION_RESUME= 2;
    MediaPlayer mPlayer = null;


    public BackgroundPlayBack() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        switch(intent.getIntExtra(playerAction, 0)) {
            case ACTION_PLAY:
                this.mPlayer = MediaPlayer.create(this, intent.getIntExtra(songToPlay, 0));
                mPlayer.start();
                break;
            case ACTION_PAUSE:
                if(this.mPlayer != null){
                    this.mPlayer.pause();
                }
                break;
            case ACTION_RESUME:
                mPlayer.start();
                break;
            default:
                break;

        }

        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mPlayer != null) this.mPlayer.release();

        Log.e(this.TAG, "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
