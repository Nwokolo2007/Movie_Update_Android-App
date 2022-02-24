package com.isep.series.viewmodels;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.isep.series.models.Entities.Series;
import com.isep.series.models.Entities.UpcomingSeries;
import com.isep.series.models.Entities.WatchList;
import com.isep.series.repository.SeriesRepository;
import com.isep.series.repository.UpcomingRepository;
import com.isep.series.repository.WatchListRepository;


import java.util.ArrayList;
import java.util.List;


public class TvSeriesViewModel extends  AndroidViewModel {

    private  SeriesRepository repository;
    public LiveData<List<Series>> allTvSeries;
    private WatchListRepository watchlistRepo;
    private UpcomingRepository upcomingRepository;
    private LiveData<List<Series>>allWatchList;
    private LiveData<List<UpcomingSeries>>allUpcomingTvSeries;

    public TvSeriesViewModel(@NonNull Application application) {
        super(application);
        repository = new SeriesRepository(application);
        watchlistRepo =  new WatchListRepository(application);
        upcomingRepository  = new UpcomingRepository(application);
        allTvSeries = repository.getAllTvSeries();
        allWatchList = repository.getWatchList();
        allUpcomingTvSeries = upcomingRepository.getAllUpcomingSeries();

    }

    public void insert(List<Series> tvSeries){
        repository.InsertAll(tvSeries);
    }

    public LiveData<List<Series>> getAllTvSeries()
    {
        return allTvSeries;
    }

    public LiveData<List<UpcomingSeries>>getAllUpcomingTvSeries(){
        return allUpcomingTvSeries;
    }

    public LiveData<List<Series>>getWatchList(){
        return allWatchList;
    }

    public void makeAPICalls()
    {
         repository.getTvSeriesFromAPI();
         upcomingRepository.getupComingSeriesFromAPI();
    }

    public void SaveWatchList(WatchList watchList)
    {
        List<WatchList>watchLists =  new ArrayList<WatchList>();
        watchLists.add(watchList);
        watchlistRepo.InsertAll(watchLists);
    }

    public void getSearch(String title)
    {
        allTvSeries =  repository.getSearch(title);
    }

}
