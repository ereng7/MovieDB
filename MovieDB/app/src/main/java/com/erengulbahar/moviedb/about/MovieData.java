package com.erengulbahar.moviedb.about;

import com.erengulbahar.moviedb.database.DBHelper;
import com.erengulbahar.moviedb.model.MovieModel;
import com.erengulbahar.moviedb.model.MovieModel2;
import com.erengulbahar.moviedb.service.MovieAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieData
{
    ArrayList<MovieModel2> movieModels2;
    ArrayList<ArrayList<String>> twoDimensional;
    ArrayList<String> titles;
    ArrayList<String> overViews;
    ArrayList<String> images;
    DBHelper dbHelper;

    public String BASE_URL = "https://api.themoviedb.org/3/";
    Retrofit retrofit;

    public MovieData(DBHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public void aboutMovie()
    {
        Gson gson = new GsonBuilder().setLenient().create(); // Here, we create gson and get it.

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) //We are writing base url here.
                .addConverterFactory(GsonConverterFactory.create(gson)) //We say getting json file to java.
                .build(); // We create retrofit.

        loadData();
    }

    public void loadData()
    {
        MovieAPI movieAPI = retrofit.create(MovieAPI.class); // We create service for data.

        twoDimensional = new ArrayList<>();
        titles = new ArrayList<>();
        overViews = new ArrayList<>();
        images = new ArrayList<>();

        Call<MovieModel> call = movieAPI.getData(); //We get data.
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response)
            {
                if(response.isSuccessful())
                {
                    MovieModel responseList = response.body();
                    movieModels2 = responseList.result;

                    for(MovieModel2 movieModel2 : movieModels2)
                    {
                        twoDimensional.add(movieModel2.genreIds); // We add datas to new ArrayList because we need to create two dimensional ArrayList because of filter regularly.
                        titles.add(movieModel2.title);
                        overViews.add(movieModel2.overView);
                        images.add(movieModel2.image);
                    }

                    dbHelper.deleteData();

                    for(int i=0;i<20;i++)
                    {
                        dbHelper.insertData(twoDimensional.get(i).get(0),titles.get(i),overViews.get(i),images.get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }
}