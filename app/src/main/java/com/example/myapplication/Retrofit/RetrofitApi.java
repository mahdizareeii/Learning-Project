package com.example.myapplication.Retrofit;

import com.example.myapplication.Models.ItemData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RetrofitApi {

    @GET("getPosts.php")
    Call<Object> getData();

    @GET()
    Call<Object> getData(@Url String url);



}
