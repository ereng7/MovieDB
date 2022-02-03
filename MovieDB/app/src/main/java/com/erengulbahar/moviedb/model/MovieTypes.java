package com.erengulbahar.moviedb.model;

import java.util.ArrayList;

public class MovieTypes
{
    public String genre;
    public ArrayList<MovieDetails> movieDetails;

    public MovieTypes(String genre, ArrayList<MovieDetails> movieDetails)
    {
        this.genre = genre;
        this.movieDetails = movieDetails;
    }
}