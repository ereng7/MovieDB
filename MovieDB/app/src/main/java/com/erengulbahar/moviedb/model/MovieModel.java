package com.erengulbahar.moviedb.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class MovieModel
{
    @SerializedName("results")
    public ArrayList<MovieModel2> result;
}