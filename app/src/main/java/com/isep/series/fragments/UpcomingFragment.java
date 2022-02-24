package com.isep.series.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.isep.series.R;
import com.isep.series.adapters.SeriesFragmentAdapter;
import com.isep.series.adapters.UpcomingFragmentAdapter;
import com.isep.series.models.Entities.Series;
import com.isep.series.models.Entities.UpcomingSeries;
import com.isep.series.viewmodels.TvSeriesViewModel;

import java.util.ArrayList;
import java.util.List;


public class UpcomingFragment extends Fragment {

    RecyclerView rvSeriesUp;
     private UpcomingFragmentAdapter upcomingFragAdapter;
    private List<UpcomingSeries> mdata =  new ArrayList<>();
    private TvSeriesViewModel viewModel;


    public UpcomingFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(TvSeriesViewModel.class);

        upcomingFragAdapter = new UpcomingFragmentAdapter(viewModel);

        viewModel.getAllUpcomingTvSeries().observe(this, new Observer<List<UpcomingSeries>>() {
            @Override
            public void onChanged(List<UpcomingSeries> seriesList) {

                if(seriesList != null )
                {
                    upcomingFragAdapter.setmData(seriesList);
                }

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        initViews(view);
        setUpSeriesAdapter();
        return view;
    }

    private void setUpSeriesAdapter() {

        rvSeriesUp.setAdapter(upcomingFragAdapter);
    }


    private void initViews(View view) {
        rvSeriesUp = view.findViewById(R.id.rv_upcoming);
        rvSeriesUp.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvSeriesUp.setHasFixedSize(true);
    }
}