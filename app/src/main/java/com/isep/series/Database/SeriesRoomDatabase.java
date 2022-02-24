package com.isep.series.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.isep.series.interfaces.SeriesDAO;
import com.isep.series.interfaces.UpcomingDAO;
import com.isep.series.interfaces.WatchListDAO;
import com.isep.series.models.Entities.Series;
import com.isep.series.models.Entities.UpcomingSeries;
import com.isep.series.models.Entities.WatchList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Series.class, WatchList.class, UpcomingSeries.class},version = 4, exportSchema = false)
public abstract class SeriesRoomDatabase  extends RoomDatabase {


    public abstract SeriesDAO seriesDAO();
    public abstract WatchListDAO watchListDAO();
    public abstract UpcomingDAO upcomingDAO();

    private static volatile SeriesRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ExecutorService getDatabaseWriteExecutor() {
        return databaseWriteExecutor;
    }

    public static SeriesRoomDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized ( SeriesRoomDatabase.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SeriesRoomDatabase.class, "TV_SeriesDatabase")
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }

            }

        }

        return INSTANCE;
    }


    public static Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyn(INSTANCE);
        }
    };

    static  class  PopulateDbAsyn extends AsyncTask<Void,Void,Void> {
        private SeriesDAO seriesDAO;
        private WatchListDAO watchListDAO;
        private UpcomingDAO upcomingDAO;
        public PopulateDbAsyn(SeriesRoomDatabase seriesRoomDatabase)
        {
            seriesDAO=seriesRoomDatabase.seriesDAO();
            watchListDAO = seriesRoomDatabase.watchListDAO();
            upcomingDAO = seriesRoomDatabase.upcomingDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            seriesDAO.DeleteAll();
            watchListDAO.DeleteAll();
            upcomingDAO.DeleteAll();
            return null;
        }
    }

}
