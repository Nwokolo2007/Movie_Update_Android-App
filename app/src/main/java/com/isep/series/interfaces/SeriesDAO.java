package com.isep.series.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RewriteQueriesToDropUnusedColumns;
import androidx.room.Transaction;

import com.isep.series.models.Entities.Series;
import com.isep.series.models.Entities.WatchList;

import java.util.List;

@Dao
public interface SeriesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(Series series);

    @Query("DELETE FROM  TV_Series")
     void DeleteAll();

    @Query("SELECT DISTINCT * FROM TV_Series")
     LiveData<List<Series>> getAllTvSeries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(List<Series>SeriesEntities);


    @Query(
            "SELECT * FROM TV_Series S JOIN Watch_List W ON S.id = W.seriesId"
    )
    @RewriteQueriesToDropUnusedColumns
    LiveData<List<Series>>getWatchList();     // return movies added in the watch list'



            @Query("SELECT * FROM TV_Series WHERE title LIKE '%' || :title || '%'")

          LiveData<List<Series>>getSearch(String title);
}
