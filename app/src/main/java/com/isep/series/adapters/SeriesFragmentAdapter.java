package com.isep.series.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.isep.series.R;
import com.isep.series.helpers.SeriesDiffCallback;
import com.isep.series.models.Entities.Series;
import com.isep.series.viewmodels.TvSeriesViewModel;

import java.util.ArrayList;
import java.util.List;

public class SeriesFragmentAdapter extends RecyclerView.Adapter<SeriesFragmentAdapter.ViewHolder> {

    List<Series> mData = new ArrayList<>();
    private TvSeriesViewModel viewModel;

    public SeriesFragmentAdapter(TvSeriesViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_series, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Series series = mData.get(position);
        //TODO: MIGHT WANT TO ADD THE SEASONS STRING TO THE MODEL INSTEAD
        //holder.series_seasons.setText(series.getSeasons());

        holder.seriesTitle.setText(series.getTitle());
        holder.episodeTitle.setText(series.getFullTitle());

        if(series.getImage() != null)
        {
            String imageUrl = series.getImage().
                    replace("http://","https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.imgSeries);
        }

    }

    @Override
    public int getItemCount() {
        if(mData != null)
            return mData.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgSeries, imgChecked;
        TextView seriesTitle, seriesSeasons, episodesLeft, episodeTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSeries = itemView.findViewById(R.id.wl_series_img);
            imgChecked = itemView.findViewById(R.id.wl_check_icon);
            seriesTitle = itemView.findViewById(R.id.wl_series_title);
            seriesSeasons = itemView.findViewById(R.id.wl_seasons);
            episodesLeft = itemView.findViewById(R.id.wl_episodes_left);
            episodeTitle = itemView.findViewById(R.id.wl_episode_title);

        }
    }

    public void setmData(List<Series> mData) {

        final SeriesDiffCallback diffCallback = new SeriesDiffCallback(this.mData, mData);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        if(this.mData != null)
        {
            this.mData.clear();
            this.mData.addAll(mData);
        }
        diffResult.dispatchUpdatesTo(this);

    }

}
