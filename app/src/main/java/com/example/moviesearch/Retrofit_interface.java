package com.example.moviesearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Retrofit_interface {
    @GET("search")
    Call<List<data_model>> test_api_get(
            @Query("text") String text);
}

