package org.mdsd2017.android.androidmp3playerdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import org.mdsd2017.android.androidmp3playerdemo.models.Song;
import org.mdsd2017.android.androidmp3playerdemo.repository.Data;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG= "In-MainActivity";
    public static final String SONG_TO_PLAY= "sonToPlay";
    private Data songsData = Data.newInstance();
    private Song currentSong;
    private TextView titleTxtVw;
    private TextView countryTxtVw;
    private TextView durationTxtVw;
    private ImageView pictureImg;
    private ImageButton playPauseBtn;
    private Boolean isPlaying = null;
    private ImageButton moreInfoBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, "On Create");


        titleTxtVw = (TextView) this.findViewById(R.id.song_title_txt_vw);
        countryTxtVw = (TextView) this.findViewById(R.id.song_country_txt_vw);
        durationTxtVw = (TextView) this.findViewById(R.id.song_duration_txt_vw);
        pictureImg = (ImageView) this.findViewById(R.id.song_picture_img);

        playPauseBtn = (ImageButton) this.findViewById(R.id.play_pause_btn);
        playPauseBtn.setOnClickListener(this);

        final ImageButton stopBtn = (ImageButton) this.findViewById(R.id.stop_btn);
        stopBtn.setOnClickListener(this);

        final ImageButton prevBtn = (ImageButton) this.findViewById(R.id.prev_btn);
        prevBtn.setOnClickListener(this);

        final ImageButton nextBtn = (ImageButton) this.findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(this);

        moreInfoBtn = (ImageButton) this.findViewById(R.id.more_info);
        moreInfoBtn.setOnClickListener(this);

        Intent intent = getIntent();
        currentSong = (Song) intent.getSerializableExtra(SONG_TO_PLAY);
        setCurrentSongInfoInView(R.drawable.pause);
        setMediaPlayerIntent(BackgroundPlayBackService.ACTION_PLAY, true, true);
        triggerRegularNotification();

        Toolbar mToolbar = (Toolbar) this.findViewById(R.id.my_toolbar_main);
        this.setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.item_about_us:
                Log.e(TAG, "About us option");
                intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.item_inspiration:
                Log.e(TAG, "Inspiration option");
                intent = new Intent(this, InspirationActivity.class);
                startActivity(intent);
                break;
            default:
                Log.e(TAG, "Other option");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e(TAG, "onDestroy");
        if(isFinishing()){
            Intent intent = new Intent(this, BackgroundPlayBackService.class);
            stopService(intent);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.e(TAG, "onConfigurationChanged");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.e(TAG, "onRestoreInstanceState");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        Log.e(TAG, "onSaveInstanceState");
    }

    /**
     * Auxiliar function to avoid code repetition. Sets all song info in the view.
     *
     * @param pausePlayButton change play/pause button for the corresponding icon
     */
    private void setCurrentSongInfoInView(int pausePlayButton){
        titleTxtVw.setText(currentSong.getTitle());
        countryTxtVw.setText(currentSong.getCountry());
        durationTxtVw.setText(currentSong.getDurationInMinutes());
        playPauseBtn.setImageResource(R.drawable.pause);
        pictureImg.setImageResource(currentSong.getImage());
        playPauseBtn.setImageResource(pausePlayButton);
        moreInfoBtn.setVisibility(View.VISIBLE);

    }

    /**
     * Auxiliar function to avoid code repetition. Creates intent and starts BackgroundPlayBackService
     *
     * @param mediaPlayerAction action to be performed
     * @param toggleIsPlaying change boolean
     * @param setNewSong indicate if song needs to be added as extra in the intent
     */
    private void setMediaPlayerIntent(int mediaPlayerAction, Boolean toggleIsPlaying, Boolean setNewSong){
        isPlaying = toggleIsPlaying;
        Intent intent = new Intent(this, BackgroundPlayBackService.class);
        intent.putExtra(BackgroundPlayBackService.PLAYER_ACTION, mediaPlayerAction);
        if(setNewSong) intent.putExtra(BackgroundPlayBackService.SONG_TO_PLAY, currentSong.getResource());
        startService(intent);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.play_pause_btn:
                Log.e(TAG, "Button PLAY/PAUSE Clicked");
                if (isPlaying == null) {
                    setCurrentSongInfoInView(R.drawable.pause);
                    setMediaPlayerIntent(BackgroundPlayBackService.ACTION_PLAY, true, true);
                    triggerRegularNotification();
                } else if(isPlaying) {
                    setMediaPlayerIntent(BackgroundPlayBackService.ACTION_PAUSE, false, false);
                    playPauseBtn.setImageResource(R.drawable.play);
                } else {
                    setMediaPlayerIntent(BackgroundPlayBackService.ACTION_RESUME, true, false);
                    playPauseBtn.setImageResource(R.drawable.pause);
                }
                break;
            case R.id.stop_btn:
                Log.e(TAG, "Button STOP Clicked");
                Intent intent = new Intent(this, BackgroundPlayBackService.class);
                isPlaying = null;
                playPauseBtn.setImageResource(R.drawable.play);
                stopService(intent);
                break;
            case R.id.prev_btn:
                Log.e(TAG, "Button PREV Clicked");
                currentSong = songsData.getPrev(currentSong);
                setCurrentSongInfoInView(R.drawable.pause);
                setMediaPlayerIntent(BackgroundPlayBackService.ACTION_PLAY, true, true);
                triggerRegularNotification();
                break;
            case R.id.next_btn:
                Log.e(TAG, "Button NEXT Clicked");
                currentSong = songsData.getNext(currentSong);
                setCurrentSongInfoInView(R.drawable.pause);
                setMediaPlayerIntent(BackgroundPlayBackService.ACTION_PLAY, true, true);
                triggerRegularNotification();
                break;
            case R.id.more_info:
                Intent songDetailIntent = new Intent(this, SongDetailActivity.class);
                songDetailIntent.putExtra(SongDetailActivity.SONG_TITLE, currentSong.getTitle());
                songDetailIntent.putExtra(SongDetailActivity.SONG_COMMENTS, currentSong.getComments());
                startActivity(songDetailIntent);
                break;
            default:
                Log.e(TAG, "Button unknown Clicked");
                break;
        }
    }


    public void triggerRegularNotification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(currentSong.getTitle())
                .setContentText(currentSong.getComments().substring(0,70)+"...")
                .setAutoCancel(true);

        Intent notificationIntent = new Intent(this, SongDetailActivity.class);
        notificationIntent.putExtra(SongDetailActivity.SONG_TITLE, currentSong.getTitle());
        notificationIntent.putExtra(SongDetailActivity.SONG_COMMENTS, currentSong.getComments());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) this .getSystemService(Service.NOTIFICATION_SERVICE);

        mNotificationManager.notify(3, mBuilder.build());

    }
}
