package com.isep.series.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.isep.series.models.Entities.Series;
import com.isep.series.models.Entities.UpcomingSeries;

import java.util.List;

public class allUpcomingSeries {

    @SerializedName("items")

    @Expose

    private List<UpcomingSeries> items = null;

    public List<UpcomingSeries> getItems() {
        return items;
    }

    public void setItems(List<UpcomingSeries> items) {
        this.items = items;
    }
}
