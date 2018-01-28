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
public class StudyFragment extends Fragment {

    private PlayerConfig youtubePlayer;
    public StudyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mylist, container, false);
        // Create a list of words
        final ArrayList<Music> musics = new ArrayList<Music>();
        musics.add(new Music(R.drawable.study4,"Beethoven's 5th Symphony","Beethoven","_4IRMYuE1hI"));
        musics.add(new Music(R.drawable.study3,"The Marriage of Figaro","Mozart","8OZCyp-LcGw"));
        musics.add(new Music(R.drawable.study2,"I Got the Keys","DJ Khaled ft. Jay-Z, Future","SFLSOIufuhM"));
        musics.add(new Music(R.drawable.sleep2,"Save Me","Meek Mill","TzvAJvi6DwA"));
        musics.add(new Music(R.drawable.study1,"Lord Knows","Meek Mill ft Tory Lanez","OYYRJZAdx2w"));
        MusicAdapter adapter = new MusicAdapter(getActivity(),musics);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //geturl("W-fFHeTX70Q&t=102s");
                Music music = musics.get(i);
                //music.getYoutubes();
                youtubePlayer.geturl(music.getYoutubes());
                //youtubePlayer.geturl("TzvAJvi6DwA");
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
