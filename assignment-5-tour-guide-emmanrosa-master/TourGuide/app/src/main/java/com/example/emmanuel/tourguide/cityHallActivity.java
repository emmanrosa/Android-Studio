package com.example.emmanuel.tourguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class cityHallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_sub);
        // Create a list of city hall
        ArrayList<ListName> city = new ArrayList<ListName>();
        city.add(new ListName(R.drawable.cityhall,"Lawrence City Hall","(978) 620-3000", "200 Common St, Lawrence, MA 01840"));
        ListAdapter adapter = new ListAdapter(this, city);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
