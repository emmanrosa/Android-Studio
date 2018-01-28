package com.example.emmanuel.tourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by emmanuel on 9/28/17.
 */

public class HostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_sub);
        // Create a list
        ArrayList<ListName> hos = new ArrayList<ListName>();
        //edu.add(new ListName(R.drawable.parthum,"Parthum Middle School","255 E Haverhill St, Lawrence, MA 01841"));
        //edu.add(new ListName(R.drawable.arlington,"Arlington Middle School","150 Arlington St, Lawrence, MA 01841"));
        hos.add(new ListName(R.drawable.general,"Lawrence General Hospital","(978) 683-4000", "1 General St, Lawrence, MA 01841"));
        ListAdapter adapter = new ListAdapter(this, hos);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
