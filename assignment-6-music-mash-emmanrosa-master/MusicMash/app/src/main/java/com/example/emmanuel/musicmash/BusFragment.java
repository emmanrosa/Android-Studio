package com.example.emmanuel.musicmash;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusFragment extends Fragment {
    private PlayerConfig youtubePlayer;

    public BusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mylist, container, false);
        // Create a list of music
        final ArrayList<Music> bus = new ArrayList<Music>();
        bus.add(new Music(R.drawable.bus1,"Relationship","Young Thug ft Future","dwWs7ZnGekc"));
        bus.add(new Music(R.drawable.bus2,"Panda","Desiigner","lsJLLEwUYZM"));
        bus.add(new Music(R.drawable.bus3,"2 Phones","Kevin Gates","oiY_iKSpWLM"));
        bus.add(new Music(R.drawable.bus4,"Congratulations","Post Malone","SC4xMk98Pdc"));
        bus.add(new Music(R.drawable.bus5,"iSpy","KYLE ft  Lil Yachty","5XK4v2fgMPU"));

        MusicAdapter adapter = new MusicAdapter(getActivity(),bus);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //
                Music music = bus.get(position);
                //
                youtubePlayer.geturl(music.getYoutubes());

                Intent intent = new Intent(getActivity(), PlayerConfig.class);
                startActivity(intent);
                //startActivity(intent);
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=lzi9embV21M"));
                //startActivity(intent);
            }
        });

        return rootView;
    }

}
