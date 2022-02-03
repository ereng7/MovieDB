package com.erengulbahar.moviedb.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class MovieModel2
{
    @SerializedName("overview")
    public String overView;

    @SerializedName("title")
    public String title;

    @SerializedName("genre_ids")
    public ArrayList<String> genreIds;

    @SerializedName("poster_path")
    public String image;
}