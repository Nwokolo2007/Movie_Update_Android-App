package com.isep.series.models.Entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "Watch_List",indices = @Index(value = {"watchListId"},unique = true))
public class WatchList {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long watchListId;

    private String seriesId;

    public Long getWatchListId() {
        return watchListId;
    }

    public void setWatchListId(@NonNull Long watchListId) {
        this.watchListId = watchListId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }
}
