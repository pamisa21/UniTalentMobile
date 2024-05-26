package com.example.unitalentv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    EditText ins_email, password;
    TextView student_name, student_ID, email, school;

    public static ProfileFragment newInstance(String userEmail) {
        ProfileFragment fragment = new ProfileFragment();

        // Pass the userEmail value as an argument to the fragment
        Bundle args = new Bundle();
        args.putString("userEmail", userEmail);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        student_name = rootView.findViewById(R.id.txtNameHolder);
        student_ID = rootView.findViewById(R.id.txtID);
        email = rootView.findViewById(R.id.txtEmail);
        school = rootView.findViewById(R.id.txtSchool);

        school.setText("Central Mindanao University");

        if (getArguments() != null) {
            String userEmail = getArguments().getString("userEmail");
            email.setText(userEmail);
        }

        return rootView;
    }
}

