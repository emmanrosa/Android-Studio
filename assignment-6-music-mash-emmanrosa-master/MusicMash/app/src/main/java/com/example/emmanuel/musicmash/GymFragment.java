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
public class GymFragment extends Fragment {

    private PlayerConfig youtubePlayer;

    public GymFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mylist, container, false);
        // Create a list of words
        final ArrayList<Music> gym = new ArrayList<Music>();
        gym.add(new Music(R.drawable.study1,"Lord Knows","Meek Mill ft Tory Lanez","OYYRJZAdx2w"));
        gym.add(new Music(R.drawable.gym,"Lil Shoota Love","Kodak Black Ft. Mr.Flipper","lzi9embV21M"));
        gym.add(new Music(R.drawable.sleep2,"Price","Meek Mill","YHcy4lfNtUc"));
        gym.add(new Music(R.drawable.sleep2,"1942 Flows","Meek Mill","qoNFGvVEp5U"));
        gym.add(new Music(R.drawable.sleep2,"Save Me","Meek Mill","OYYRJZAdx2w"));
        MusicAdapter adapter = new MusicAdapter(getActivity(),gym);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //
                Music music = gym.get(position);
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
