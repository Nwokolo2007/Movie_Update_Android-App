package com.isep.series.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.isep.series.Database.SeriesRoomDatabase;
import com.isep.series.interfaces.WatchListDAO;
import com.isep.series.models.Entities.WatchList;

import java.util.List;

public class WatchListRepository {
    public WatchListDAO watchListDAO;
    private SeriesRoomDatabase database;
    public LiveData<List<WatchList>> allWatchList;

    public WatchListRepository(Application application){
        database= SeriesRoomDatabase.getDatabase(application);
        watchListDAO = database.watchListDAO();


    }



    public void InsertAll(List<WatchList> watchLists){
        new InsertAsyncTask(watchListDAO).execute(watchLists);

    }

    public LiveData<List<WatchList>> getAllUsersWatchList(){
        return allWatchList;
    }


    private static class InsertAsyncTask extends AsyncTask<List<WatchList>,Void,Void> {
        private WatchListDAO watchListDAO;

        public InsertAsyncTask(WatchListDAO watchListDAO)
        {
            this.watchListDAO = watchListDAO;
        }

        @Override
        protected Void doInBackground(List<WatchList>... lists) {
            watchListDAO.InsertAllWatchLists(lists[0]);
            return null;
        }
    }
}
