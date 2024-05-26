package com.example.unitalentv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class fragment1 extends Fragment {
    private DatabaseReference databaseRef;
    private ArtsAdapter artAdapter;
    private List<Upload> uploadList;
    private ProgressBar progressCircle;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment1, container, false);

        // Find views by their IDs
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewArts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressCircle = rootView.findViewById(R.id.progress_circle);

        uploadList = new ArrayList<>();

        databaseRef = FirebaseDatabase.getInstance().getReference("arts");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                uploadList.clear();

                for (DataSnapshot postSnapShot : snapshot.getChildren()){
                    Upload upload = postSnapShot.getValue(Upload.class);
                    uploadList.add(upload);
                }

                artAdapter = new ArtsAdapter(getContext(), uploadList);
                recyclerView.setAdapter(artAdapter);
                progressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressCircle.setVisibility(View.INVISIBLE);
            }
        });
        return rootView;
    }
}