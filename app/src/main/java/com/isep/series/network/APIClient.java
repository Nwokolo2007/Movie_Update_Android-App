package com.isep.series.network;

import com.isep.series.models.allTvSeries;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class APIClient {

    private static APIClient client =  null;
    private static String BASE_URL = "https://imdb-api.com/en/API/";
    private  API myApi;



    public APIClient()
    {

        httpInterceptor interceptor =  new httpInterceptor();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        myApi = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(API.class);
    }

    public API getMyApi() {
        return myApi;
    }
}
