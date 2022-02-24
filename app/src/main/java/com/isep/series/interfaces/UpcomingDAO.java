package com.isep.series.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.isep.series.models.Entities.Series;
import com.isep.series.models.Entities.UpcomingSeries;

import java.util.List;

@Dao
public interface UpcomingDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(UpcomingSeries series);

    @Query("DELETE FROM  UpcomingSeries_List")
    void DeleteAll();

    @Query("SELECT DISTINCT * FROM UpcomingSeries_List")
    LiveData<List<UpcomingSeries>> getAllUpcomingTvSeries();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(List<UpcomingSeries>SeriesEntities);
}
