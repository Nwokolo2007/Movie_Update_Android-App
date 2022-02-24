package com.isep.series.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.isep.series.Database.SeriesRoomDatabase;
import com.isep.series.interfaces.UpcomingDAO;
import com.isep.series.interfaces.WatchListDAO;
import com.isep.series.models.Entities.UpcomingSeries;
import com.isep.series.models.Entities.WatchList;
import com.isep.series.models.allTvSeries;
import com.isep.series.models.allUpcomingSeries;
import com.isep.series.network.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingRepository {

    public UpcomingDAO upcomingDAO;
    private SeriesRoomDatabase database;
    public LiveData<List<UpcomingSeries>> allUpcomingTvSeries;
    private APIClient apiClient =  new APIClient();

    public UpcomingRepository(Application application){
        database= SeriesRoomDatabase.getDatabase(application);
        upcomingDAO = database.upcomingDAO();

        allUpcomingTvSeries = upcomingDAO.getAllUpcomingTvSeries();


    }



    public void InsertAll(List<UpcomingSeries> upcomingSeries){
        new UpcomingRepository.InsertAsyncTask(upcomingDAO).execute(upcomingSeries);

    }

    public LiveData<List<UpcomingSeries>> getAllUpcomingSeries(){
        return allUpcomingTvSeries;
    }




    public void getupComingSeriesFromAPI() {

        apiClient.getMyApi().getAllUpcomingSeries(20)
                .enqueue(new Callback<allUpcomingSeries>() {
                    @Override
                    public void onResponse(Call<allUpcomingSeries> call, Response<allUpcomingSeries> response) {
                        if(response.body() != null)
                        {
                            InsertAll(response.body().getItems());
                        }
                    }

                    @Override
                    public void onFailure(Call<allUpcomingSeries> call, Throwable t) {
                        Log.d("main", "onFailure: "+t.getMessage());
                    }
                });
    }


    private static class InsertAsyncTask extends AsyncTask<List<UpcomingSeries>,Void,Void> {
        private UpcomingDAO upcomingDAO;

        public InsertAsyncTask(UpcomingDAO upcomingDAO)
        {
            this.upcomingDAO = upcomingDAO;
        }

        @Override
        protected Void doInBackground(List<UpcomingSeries>... lists) {
            upcomingDAO.InsertAll(lists[0]);
            return null;
        }
    }
}
