package com.erengulbahar.moviedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import com.erengulbahar.moviedb.about.MovieData;
import com.erengulbahar.moviedb.adapter.ParentAdapter;
import com.erengulbahar.moviedb.database.DBHelper;
import com.erengulbahar.moviedb.databinding.ActivityMainBinding;
import com.erengulbahar.moviedb.model.MovieDetails;
import com.erengulbahar.moviedb.model.MovieTypes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding activityMainBinding;
    MovieData movieData;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<String> titles;
    ArrayList<String> overViews;
    ArrayList<String> genres;
    ArrayList<String> images;
    ArrayList<MovieDetails> movieDetails1;
    ArrayList<MovieDetails> movieDetails2;
    ArrayList<MovieDetails> movieDetails3;
    ArrayList<MovieDetails> movieDetails4;
    ArrayList<MovieDetails> movieDetails5;
    ArrayList<MovieDetails> movieDetails6;
    ArrayList<MovieDetails> movieDetails7;
    ArrayList<MovieTypes> movieTypes;
    Cursor cursor;
    String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w200";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        dbHelper = new DBHelper(MainActivity.this);
        sqLiteDatabase = dbHelper.getReadableDatabase();

        movieData = new MovieData(dbHelper);
        movieData.aboutMovie();

        showData();
    }

    public void showData()
    {
        titles = new ArrayList<>();
        overViews = new ArrayList<>();
        genres = new ArrayList<>();
        images = new ArrayList<>();
        movieDetails1 = new ArrayList<>();
        movieDetails2 = new ArrayList<>();
        movieDetails3 = new ArrayList<>();
        movieDetails4 = new ArrayList<>();
        movieDetails5 = new ArrayList<>();
        movieDetails6 = new ArrayList<>();
        movieDetails7 = new ArrayList<>();
        movieTypes = new ArrayList<>();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM Movie",null);

        while(cursor.moveToNext())
        {
            int genre = cursor.getColumnIndex("Genre");
            int title = cursor.getColumnIndex("Title");
            int overView = cursor.getColumnIndex("Overview");
            int image = cursor.getColumnIndex("Image");

            genres.add(cursor.getString(genre));
            titles.add(cursor.getString(title));
            overViews.add(cursor.getString(overView));
            images.add(IMAGE_BASE_URL+cursor.getString(image));
        }

        for(int i=0;i<genres.size();i++)
        {
            if(genres.get(i).equals("28"))
            {
                movieDetails1.add(new MovieDetails(titles.get(i), overViews.get(i), images.get(i)));
            }

            else if(genres.get(i).equals("16"))
            {
                movieDetails2.add(new MovieDetails(titles.get(i), overViews.get(i), images.get(i)));
            }

            else if(genres.get(i).equals("35"))
            {
                movieDetails3.add(new MovieDetails(titles.get(i), overViews.get(i), images.get(i)));
            }

            else if(genres.get(i).equals("27"))
            {
                movieDetails4.add(new MovieDetails(titles.get(i), overViews.get(i), images.get(i)));
            }

            else if(genres.get(i).equals("878"))
            {
                movieDetails5.add(new MovieDetails(titles.get(i), overViews.get(i), images.get(i)));
            }

            else if(genres.get(i).equals("53"))
            {
                movieDetails6.add(new MovieDetails(titles.get(i), overViews.get(i), images.get(i)));
            }

            else if(genres.get(i).equals("10770"))
            {
                movieDetails7.add(new MovieDetails(titles.get(i), overViews.get(i), images.get(i)));
            }
        }

        movieTypes.add(new MovieTypes("Aksiyon",movieDetails1));
        movieTypes.add(new MovieTypes("Animasyon",movieDetails2));
        movieTypes.add(new MovieTypes("Komedi",movieDetails3));
        movieTypes.add(new MovieTypes("Korku",movieDetails4));
        movieTypes.add(new MovieTypes("Bilim-Kurgu",movieDetails5));
        movieTypes.add(new MovieTypes("Gerilim",movieDetails6));
        movieTypes.add(new MovieTypes("TV Film",movieDetails7));

        activityMainBinding.parentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ParentAdapter parentAdapter = new ParentAdapter(movieTypes,getApplicationContext());
        activityMainBinding.parentRecyclerView.setAdapter(parentAdapter);

        cursor.close();
    }
}