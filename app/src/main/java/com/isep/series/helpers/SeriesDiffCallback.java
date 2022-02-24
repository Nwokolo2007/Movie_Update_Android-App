package com.isep.series.helpers;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.isep.series.models.Entities.Series;

import java.util.List;

public  class SeriesDiffCallback extends  DiffUtil.Callback {
    private final List<Series> mOldTVSeriesList;
    private final List<Series> mNewTVSeriesList;

    public SeriesDiffCallback(List<Series> mOldTVSeriesList, List<Series> mNewTVSeriesList) {
        this.mOldTVSeriesList = mOldTVSeriesList;
        this.mNewTVSeriesList = mNewTVSeriesList;
    }

    @Override
    public int getOldListSize() {

        int oldSize= 0;
        if(mOldTVSeriesList != null)
        {
            oldSize = mOldTVSeriesList.size();
        }
        return oldSize;
    }

    @Override
    public int getNewListSize() {
        int newSize = 0;
        if(mNewTVSeriesList != null)
        {
            newSize = mNewTVSeriesList.size();
        }

        return newSize;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldTVSeriesList.get(oldItemPosition).getId() == mNewTVSeriesList.get(
                newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Series oldSeries = mOldTVSeriesList.get(oldItemPosition);
        final Series newSeries = mNewTVSeriesList.get(newItemPosition);

        return oldSeries.getId().equals(newSeries.getId());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
