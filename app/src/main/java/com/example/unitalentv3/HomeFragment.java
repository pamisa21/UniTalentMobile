package com.example.unitalentv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Find views by their IDs
        tabLayout = rootView.findViewById(R.id.tabLayout);
        viewPager = rootView.findViewById(R.id.viewPager);

        // Rest of your code...
        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new fragment1(), "Arts");
        vpAdapter.addFragment(new fragment2(), "Poem");
        vpAdapter.addFragment(new fragment3(), "Music");
        viewPager.setAdapter(vpAdapter);

        return rootView;
    }
}
