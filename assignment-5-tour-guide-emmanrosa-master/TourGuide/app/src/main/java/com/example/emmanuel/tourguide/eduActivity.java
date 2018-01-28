package com.example.emmanuel.tourguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class eduActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_sub);
        // Create a list of schools
        ArrayList<ListName> edu = new ArrayList<ListName>();
        edu.add(new ListName(R.drawable.parthum,"Parthum Middle School","(978) 691-7224","255 E Haverhill St, Lawrence, MA 01841"));
        edu.add(new ListName(R.drawable.arlington,"Arlington Middle School","(978) 975-5930","150 Arlington St, Lawrence, MA 01841"));
        edu.add(new ListName(R.drawable.frost,"Robert Frost Elementary School","(978) 975-5941","33 Hamlet St, Lawrence, MA 01843"));
        edu.add(new ListName(R.drawable.lhs,"Lawrence High School Campus","(978) 689-8222","70-71 N Parish Rd, Lawrence, MA 01843"));
        edu.add(new ListName(R.drawable.cchs,"Central Catholic High School","(978) 682-0260","300 Hampshire St, Lawrence, MA 01841"));
        edu.add(new ListName(R.drawable.north,"Notre Dame Cristo Rey High School","(978) 689-8222","303 Haverhill St, Lawrence, MA 01841"));
        ListAdapter adapter = new ListAdapter(this, edu);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
