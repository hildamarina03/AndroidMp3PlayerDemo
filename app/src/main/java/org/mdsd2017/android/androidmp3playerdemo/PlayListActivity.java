package org.mdsd2017.android.androidmp3playerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.mdsd2017.android.androidmp3playerdemo.adapters.MyListAdapter;
import org.mdsd2017.android.androidmp3playerdemo.models.Song;
import org.mdsd2017.android.androidmp3playerdemo.repository.Data;

public class PlayListActivity extends AppCompatActivity implements MyListAdapter.ListInteractionListener {
    private static final String TAG = "In-PlayListActivity";
    private Data songsData = Data.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        final ListView listView = (ListView) findViewById(R.id.list_view);

        MyListAdapter adapter = new MyListAdapter(this, 0, songsData.getSongsArray());
        listView.setAdapter(adapter);
        adapter.setListener(this);


        Toolbar mToolbar = (Toolbar) this.findViewById(R.id.my_toolbar_main);
        this.setSupportActionBar(mToolbar);
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
            default:
                Log.e(TAG, "Other option");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSongSelected(Song song) {
        Log.i(TAG, "onSongSelected");

        Intent intent = new Intent(this, SongPlayerActivity.class);
        intent.putExtra(SongPlayerActivity.SONG_TO_PLAY, song);
        startActivity(intent);

    }
}

