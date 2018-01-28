package com.example.emmanuel.tourguide;

/**
 * Created by emmanuel on 9/30/17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class ListAdapter extends ArrayAdapter<ListName>{

    public  ListAdapter (Context context, ArrayList<ListName> ls) {
        super(context, 0, ls);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.mylist, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        ListName currentlist = getItem(position);

        ImageView  images = (ImageView) listItemView.findViewById(R.id.imageicon);
        images.setImageResource(currentlist.getImages());
        images.setVisibility(View.VISIBLE);

        // name
        TextView TextView1 = (TextView) listItemView.findViewById(R.id.Itemname);
        TextView1.setText(currentlist.getNames());
        // phone
        TextView TextView2 = (TextView) listItemView.findViewById(R.id.Itemphone);
        TextView2.setText(currentlist.getPhones());
        // address
        TextView TextView3 = (TextView) listItemView.findViewById(R.id.Itemaddress);
        TextView3.setText(currentlist.getAddresss());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
