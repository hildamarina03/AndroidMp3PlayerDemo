package org.mdsd2017.android.androidmp3playerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SongDetailActivity extends AppCompatActivity implements View.OnClickListener  {

    private static final String TAG= "In-SongDetailActivity";

    public static final String SONG_TITLE= "songTitle";
    public static final String SONG_COMMENTS= "songComments";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        Intent intent = getIntent();

        final TextView songTitle = (TextView) findViewById(R.id.song_title_2_txt_vw);
        final TextView songDescription = (TextView) findViewById(R.id.song_comments);

        final ImageButton exitBtn = (ImageButton) this.findViewById(R.id.exit_img_btn);
        exitBtn.setOnClickListener(this);

        songTitle.setText(String.valueOf(intent.getStringExtra(SONG_TITLE)));
        songDescription.setText(intent.getStringExtra(SONG_COMMENTS));

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.exit_img_btn:
                Log.e(TAG, "Button EXIT Clicked");
                this.finish();
                break;
            default:
                Log.e(TAG, "Button unknown Clicked");
                break;
        }
    }
}
