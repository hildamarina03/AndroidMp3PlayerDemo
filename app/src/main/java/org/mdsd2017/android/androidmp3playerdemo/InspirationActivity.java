package org.mdsd2017.android.androidmp3playerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.mdsd2017.android.androidmp3playerdemo.repository.Data;

import java.util.Random;

public class InspirationActivity extends AppCompatActivity implements View.OnClickListener  {

    private static final String TAG= "In-AppCompatActivity";
    private Data mainData = Data.newInstance();
    private final int[] INSPIRATION_SOURCE = mainData.getInspiration();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspiration);

        final ImageButton exitBtn = (ImageButton) this.findViewById(R.id.exit_img_btn);
        exitBtn.setOnClickListener(this);

        ImageView inspirationPicture = (ImageView) this.findViewById(R.id.insp_picture_img);
        inspirationPicture.setImageResource(INSPIRATION_SOURCE[randomNumber()]);
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

    private int randomNumber(){
        Random r = new Random();
        int Low = 0;
        int High = INSPIRATION_SOURCE.length-1;

        return r.nextInt(High-Low) + Low;
    }
}
