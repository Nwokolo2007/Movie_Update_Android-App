package com.isep.series.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.isep.series.R;
import com.isep.series.fragments.DiscoverFragment;
import com.isep.series.fragments.ProfileFragment;
import com.isep.series.fragments.SeriesFragment;
import com.isep.series.repository.SeriesRepository;
import com.isep.series.viewmodels.TvSeriesViewModel;

public class MainActivity extends AppCompatActivity {

    private TvSeriesViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SeriesFragment()).commit();
        viewModel =  new ViewModelProvider(this).get(TvSeriesViewModel.class);
        viewModel.makeAPICalls(); // make the api call here to populate db with data
    }

    private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.nav_series:
                    selectedFragment = new SeriesFragment();
                    break;
                case R.id.nav_discover:
                    selectedFragment = new DiscoverFragment(getSupportFragmentManager());
                    break;
                case R.id.nav_profile:
                selectedFragment = new ProfileFragment();
                break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
}