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
public class SleepFragment extends Fragment {
    private PlayerConfig youtubePlayer;

    public SleepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.mylist, container, false);
        // Create a list of words
        final ArrayList<Music> musics = new ArrayList<Music>();
        musics.add(new Music(R.drawable.sleep5,"Bank Account","21 Savage","sV2t3tW_JTQ"));
        musics.add(new Music(R.drawable.sleep1,"FourFiveSeconds","Rihanna, Paul McCartney, Kanye West","kt0g4dWxEBo"));
        musics.add(new Music(R.drawable.s3,"Mask Off","Future","xvZqHgFz51I"));
        musics.add(new Music(R.drawable.sleep4,"1-800-273-8255","Logic","Kb24RrHIbFk"));
        MusicAdapter adapter = new MusicAdapter(getActivity(),musics);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //
                Music music = musics.get(position);
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
