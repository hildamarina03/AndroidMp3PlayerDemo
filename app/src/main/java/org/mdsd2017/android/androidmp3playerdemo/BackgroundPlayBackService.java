package org.mdsd2017.android.androidmp3playerdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import java.io.IOException;

public class BackgroundPlayBackService extends Service  {

    private static final String TAG= "In-BackgroundPlayBack";
    public static final String PLAYER_ACTION= "playerAction";
    public static final String SONG_TO_PLAY= "songToPlay";
    public static final int ACTION_PLAY= 0;
    public static final int ACTION_PAUSE= 1;
    public static final int ACTION_RESUME= 2;
    private final String SONG_URI = "android.resource://org.mdsd2017.android.androidmp3playerdemo/raw/";
    private MediaPlayer mPlayer = null;


    public BackgroundPlayBackService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        switch(intent.getIntExtra(PLAYER_ACTION, 0)) {
            case ACTION_PLAY:
                if (this.mPlayer == null) {
                    this.mPlayer = MediaPlayer.create(this, intent.getIntExtra(SONG_TO_PLAY, 0));
                } else {
                    try {
                        this.mPlayer.reset();
                        this.mPlayer.setDataSource(this, Uri.parse(SONG_URI+intent.getIntExtra(SONG_TO_PLAY, 0)));
                        this.mPlayer.prepare();
                    }
                    catch (IllegalArgumentException e)
                    {
                        Log.e(TAG, "Unable to play audio queue do to exception: " + e.getMessage(), e);
                    }
                    catch (IllegalStateException e)
                    {
                        Log.e(TAG, "Unable to play audio queue do to exception: " + e.getMessage(), e);
                    }
                    catch (IOException e)
                    {
                        Log.e(TAG, "Unable to play audio queue do to exception: " + e.getMessage(), e);
                    }
                }
                mPlayer.start();
                Log.e(TAG, "Play");
                break;
            case ACTION_PAUSE:
                if(this.mPlayer != null){
                    this.mPlayer.pause();
                }
                Log.e(TAG, "Pause");
                break;
            case ACTION_RESUME:
                mPlayer.start();
                Log.e(TAG, "Resume");
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
        Log.e(TAG, "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
