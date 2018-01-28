package com.example.emmanuel.tourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by emmanuel on 9/28/17.
 */

public class ParkActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_sub);
        // Create a list of parks
        ArrayList<ListName> park = new ArrayList<ListName>();
        park.add(new ListName(R.drawable.campagnone,"Campagnone Common","(978) 620-3250","200 Common St, Lawrence, MA 01840"));
        park.add(new ListName(R.drawable.mountvernonpark," Mount Vernon Park","(978) 620-3000","Mt Vernon Park, Lawrence, MA 01843"));
        park.add(new ListName(R.drawable.heritage,"Lawrence Heritage State Park", "(978) 794-1655","1 Jackson St, Lawrence, MA 01840"));
        park.add(new ListName(R.drawable.scarito,"Dr. Nina Scarito Park","(978) 620-3000","49 Brook St, Lawrence, MA 01841"));
        ListAdapter adapter = new ListAdapter(this, park);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
