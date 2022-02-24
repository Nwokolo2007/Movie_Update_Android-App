package com.isep.series.network;
import com.isep.series.models.allTvSeries;
import com.isep.series.models.allUpcomingSeries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface API {

    @GET("Top250TVs/k_o0k4l2d3")
    Call<allTvSeries> getAllTvSeries(@Query("limit") int limit);

    @GET("ComingSoon/k_o0k4l2d3")
    Call<allUpcomingSeries>getAllUpcomingSeries(@Query("limit") int limit);

}
