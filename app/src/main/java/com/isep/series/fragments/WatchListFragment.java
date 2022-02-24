package com.isep.series.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.isep.series.R;
import com.isep.series.adapters.DiscoverAdapter;
import com.isep.series.adapters.SeriesFragmentAdapter;
import com.isep.series.models.Entities.Series;
import com.isep.series.viewmodels.TvSeriesViewModel;


import java.util.ArrayList;
import java.util.List;


public class WatchListFragment extends Fragment {

    private RecyclerView rvSeries;
    private SeriesFragmentAdapter seriesFragAdapter;
    private List<Series> mdata;
    private TvSeriesViewModel viewModel;



    public WatchListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(TvSeriesViewModel.class);

        seriesFragAdapter = new SeriesFragmentAdapter(viewModel);

        viewModel.getWatchList().observe(this, new Observer<List<Series>>() {
            @Override
            public void onChanged(List<Series> seriesList) {

                if(seriesList != null )
                {
                    seriesFragAdapter.setmData(seriesList);
                    Log.d("watchlist", "onChanged count of list: " + seriesList.size() );
                }

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.d("watchlist", "Inflating watchlist fragment" );

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_watch_list, container, false);
        //RECYCLER VIEW ADAPTER SETUP
        rvSeries = view.findViewById(R.id.rv_watchlist);
        rvSeries.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvSeries.setAdapter(seriesFragAdapter);
        return view;
    }

     private void setUpSeriesAdapter() {
        rvSeries.setAdapter(seriesFragAdapter);
    }


}