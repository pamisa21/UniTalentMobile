package com.example.unitalentv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class fragment2 extends Fragment {

    private PoemsAdapter poemsAdapter;
    private DatabaseReference databaseRef;
    private List<UploadPoem> uploadPoemList;
    private ProgressBar progressCircle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment2, container, false);

        // Find views by their IDs
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewPoems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressCircle = rootView.findViewById(R.id.progress_circle);

        uploadPoemList = new ArrayList<>();
        databaseRef = FirebaseDatabase.getInstance().getReference("poems");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                uploadPoemList.clear();

                for (DataSnapshot postSnapShot : snapshot.getChildren()){
                    UploadPoem uploadPoem = postSnapShot.getValue(UploadPoem.class);
                    uploadPoemList.add(uploadPoem);
                }

                poemsAdapter = new PoemsAdapter(getContext(), uploadPoemList);
                recyclerView.setAdapter(poemsAdapter);
                progressCircle.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                progressCircle.setVisibility(View.INVISIBLE);
            }
        });
        return rootView;
    }
}