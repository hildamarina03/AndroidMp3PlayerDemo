package org.mdsd2017.android.androidmp3playerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG= "In-AppCompatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        final ImageButton exitBtn = (ImageButton) this.findViewById(R.id.exit_img_btn);
        exitBtn.setOnClickListener(this);

        Toolbar mToolbar = (Toolbar) this.findViewById(R.id.my_toolbar_main);
        this.setSupportActionBar(mToolbar);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.exit_img_btn:
                Log.e(TAG, "Button EXIT Clicked");
                finish();
                break;
            default:
                Log.e(TAG, "Button unknown Clicked");
                break;
        }
    }





}
