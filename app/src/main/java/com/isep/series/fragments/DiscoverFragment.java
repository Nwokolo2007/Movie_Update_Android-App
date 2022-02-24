package com.isep.series.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.isep.series.R;
import com.isep.series.adapters.DiscoverAdapter;
import com.isep.series.models.Entities.Series;
import com.isep.series.viewmodels.TvSeriesViewModel;

import java.util.List;

public class DiscoverFragment extends Fragment {

    private RecyclerView discoverRV;
    private TvSeriesViewModel viewModel;
    private DiscoverAdapter discoverAdapter;
    private FragmentManager fragmentManager;

    public DiscoverFragment(FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(TvSeriesViewModel.class);

        discoverAdapter = new DiscoverAdapter(viewModel,fragmentManager);

        viewModel.getAllTvSeries().observe(this, new Observer<List<Series>>() {
            @Override
            public void onChanged(List<Series> seriesList) {

                discoverAdapter.setmData(seriesList);
                Log.d("main", "onChanged: "+ seriesList.get(0).getFullTitle());
            }
        });
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        EditText searchEdit =  (EditText) view.findViewById(R.id.search_input);
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.getSearch(charSequence.toString().trim());
                Log.d("afterTextChanged",charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

                viewModel.getSearch(editable.toString().trim());
                Log.d("afterTextChanged","here");

            }
        });



        //sets status bar to transparent
//        Window w = getActivity().getWindow();
//        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //RECYCLER VIEW ADAPTER SETUP
        discoverRV = view.findViewById(R.id.rv_discover_series);

        discoverRV.setLayoutManager(new LinearLayoutManager(view.getContext()));
        discoverRV.setAdapter(discoverAdapter);
        return view;
    }


}
