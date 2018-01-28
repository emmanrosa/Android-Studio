package com.example.emmanuel.tourguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class restActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_sub);
        // Create a list of schools
        ArrayList<ListName> rest = new ArrayList<ListName>();
        rest.add(new ListName(R.drawable.cazeazteca,"Cafe Azteca","(978) 689-7393","180 Common St, Lawrence, MA 01840"));
        rest.add(new ListName(R.drawable.pollot,"Pollo Tipico","(978) 975-8013","190 Lawrence St, Lawrence, MA 01841"));
        rest.add(new ListName(R.drawable.gandz,"G & Z Pizza & Restaurant","(978) 975-0906","55 Berkeley St, Lawrence, MA 01841"));
        rest.add(new ListName(R.drawable.salvatores,"Salvatore's","(978) 291-0220","354 Merrimack St, Lawrence, MA 01843"));
        rest.add(new ListName(R.drawable.korean,"Garden House Korean Restaurant","(978) 691-5448","108 Winthrop Ave, Lawrence, MA 01843"));
        rest.add(new ListName(R.drawable.inaka,"Inaka Japanese Restaurant","(978) 794-9806","160 Winthrop Ave, Lawrence, MA 01843"));
        rest.add(new ListName(R.drawable.lee,"Lee Chen Chinese","(978) 689-9888","230 Winthrop Ave, Lawrence, MA 01843"));
        rest.add(new ListName(R.drawable.rancho,"Rancho Brazil","(978) 208-4348","13 S Broadway, Lawrence, MA 01843"));
        rest.add(new ListName(R.drawable.pub,"The Claddagh Pub","(978) 688-8337","399 N Canal St, Lawrence, MA 01840"));
        rest.add(new ListName(R.drawable.napoli,"Napoli Pizza & Subs","(978) 686-8172","79 Common St, Lawrence, MA 01840"));
        ListAdapter adapter = new ListAdapter(this, rest);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
