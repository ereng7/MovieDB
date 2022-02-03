package com.erengulbahar.moviedb.service;

import com.erengulbahar.moviedb.model.MovieModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPI
{
    @GET("movie/popular?api_key=<<ENTER_YOUR_API>>&language=tr&page=1")
    Call<MovieModel> getData();
}