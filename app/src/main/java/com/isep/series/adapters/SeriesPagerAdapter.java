package com.isep.series.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.isep.series.fragments.UpcomingFragment;
import com.isep.series.fragments.WatchListFragment;

import java.util.ArrayList;
import java.util.List;

public class SeriesPagerAdapter extends FragmentStateAdapter {

    private String[] titles = new String[] {"WatchList", "Upcoming"};


    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();


    public SeriesPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new WatchListFragment();
            case 1:
                return new UpcomingFragment();

        }
        return new WatchListFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }


}
