package com.example.unitalentv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class fragment3 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_fragment3, container, false);

        // Find views by their IDs
        RecyclerView recyclerView = viewRoot.findViewById(R.id.recyclerViewMusic);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        //Your Code Here
        MusicData[] musicData = new MusicData[] {
                new MusicData("interface", "By Ervin John Libardos", R.drawable.music_1),
                new MusicData("fullstack", "By Klent Baylosis", R.drawable.music_2),
                new MusicData("Promise?", "By Dexther Remadadia (ft. Gabriel Catane)", R.drawable.music_3),
                new MusicData("hostname pres", "By Karl Kinji Landicho", R.drawable.music_4),
                new MusicData("Proposal", "By Ryell Andrade", R.drawable.music_5),
                new MusicData("oh boy", "By Elishapat Juan", R.drawable.music_6),
                new MusicData("Ku Lo Sa", "By Rosette Ayunar", R.drawable.music_7),
                new MusicData("hatid sundo", "By Jhomar Dagoc", R.drawable.music_8),
                new MusicData("blonde", "By Frank Dagat", R.drawable.music_9),
        };

        MusicAdapter musicAdapter = new MusicAdapter(musicData, fragment3.this);
        recyclerView.setAdapter(musicAdapter);

        return viewRoot;

    }




}