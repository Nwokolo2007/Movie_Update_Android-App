package com.isep.series.adapters;

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
import com.isep.series.helpers.UpcomingSeriesDiffCallback;
import com.isep.series.models.Entities.Series;
import com.isep.series.models.Entities.UpcomingSeries;
import com.isep.series.viewmodels.TvSeriesViewModel;

import java.util.ArrayList;
import java.util.List;

public class UpcomingFragmentAdapter extends RecyclerView.Adapter<UpcomingFragmentAdapter.ViewHolder> {

    List<UpcomingSeries> mData = new ArrayList<>();
    private  TvSeriesViewModel viewModel;

    public UpcomingFragmentAdapter(TvSeriesViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upcoming_series, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UpcomingSeries series = mData.get(position);
        //TODO: MIGHT WANT TO ADD THE SEASONS STRING TO THE MODEL INSTEAD

        holder.date.setText(series.getReleaseState());
        holder.seriesTitle.setText(series.getTitle());
        holder.episodeTitle.setText(series.getFullTitle());
        //holder.series_seasons.setText(series.getSeasons());
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
        TextView seriesTitle, seriesSeasons, episodesLeft, episodeTitle, date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSeries = itemView.findViewById(R.id.up_series_img);
            imgChecked = itemView.findViewById(R.id.up_check_icon);
            seriesTitle = itemView.findViewById(R.id.up_series_title);
            seriesSeasons = itemView.findViewById(R.id.up_seasons);
            episodesLeft = itemView.findViewById(R.id.up_episodes_left);
            episodeTitle = itemView.findViewById(R.id.up_episode_title);
            date = itemView.findViewById(R.id.up_date);

        }
    }


    public void setmData(List<UpcomingSeries> mData) {

        final UpcomingSeriesDiffCallback diffCallback = new UpcomingSeriesDiffCallback(this.mData, mData);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        if(this.mData != null)
        {
            this.mData.clear();
            this.mData.addAll(mData);
        }
        diffResult.dispatchUpdatesTo(this);

    }
}
