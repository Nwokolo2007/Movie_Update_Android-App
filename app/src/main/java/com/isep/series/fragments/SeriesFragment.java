package com.isep.series.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.isep.series.R;
import com.isep.series.adapters.SeriesPagerAdapter;

import java.util.List;

public class SeriesFragment extends Fragment {

    private String[] titles = new String[] {"WatchList", "Upcoming"};

    SeriesPagerAdapter seriesPagerAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


//        getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_series, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        FragmentManager  fm = getActivity().getSupportFragmentManager();
        viewPager2 = view.findViewById(R.id.series_viewpager);
        tabLayout = view.findViewById(R.id.seriesTabLay);
        seriesPagerAdapter = new SeriesPagerAdapter(fm, getLifecycle());

        viewPager2.setAdapter(seriesPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> tab.setText(titles[position]))).attach();
    }


}
