package org.mdsd2017.android.androidmp3playerdemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.mdsd2017.android.androidmp3playerdemo.R;
import org.mdsd2017.android.androidmp3playerdemo.models.Song;


public class MyListAdapter extends ArrayAdapter<Song> {

    private Context mContext;
    private Song[] songList;
    private ListInteractionListener listener;

    public MyListAdapter(Context context, int resource, Song[] objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.songList = objects;
    }

    public void setListener(ListInteractionListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        LayoutInflater mInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = mInflater.inflate(R.layout.row_layout, null);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSongSelected(songList[position]);
            }
        });
        TextView listRowTitle_TxtView = (TextView) rowView.findViewById(R.id.tv_title_row_layout);
        listRowTitle_TxtView.setText(songList[position].getTitle());
        ImageView listRowIcon_ImgView = (ImageView) rowView.findViewById(R.id.image_view_icon_row_layout);
        listRowIcon_ImgView.setImageResource(songList[position].getImage());

        return rowView;
    }

    public interface ListInteractionListener {
        void onSongSelected(Song song);
    }
}
