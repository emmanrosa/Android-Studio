package com.example.emmanuel.musicmash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by emmanuel on 10/5/17.
 */

public class MusicAdapter extends ArrayAdapter<Music> {
   // private int mColorResourceId;
    public  MusicAdapter (Context context, ArrayList<Music> ls) {
        super(context, 0, ls);
        //mColorResourceId = colorResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        // Get the {@link Music} object located at this position in the list
        Music currentlist = getItem(position);
        // image
        ImageView images = (ImageView) listItemView.findViewById(R.id.imageicon);
        images.setImageResource(currentlist.getImages());
        images.setVisibility(View.VISIBLE);
        // name
        TextView TextView1 = (TextView) listItemView.findViewById(R.id.sname);
        TextView1.setText(currentlist.getNames());
        // title
        TextView TextView2 = (TextView) listItemView.findViewById(R.id.stitle);
        TextView2.setText(currentlist.getTitles());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
