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
import java.util.Map;
@Dao
public interface WatchListDAO {

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    void SaveWatchList(WatchList watchList);*/

    @Query("DELETE FROM  Watch_List")
    void DeleteAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAllWatchLists(List<WatchList>watchLists);


}
