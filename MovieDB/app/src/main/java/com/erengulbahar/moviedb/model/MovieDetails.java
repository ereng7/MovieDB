package com.erengulbahar.moviedb.model;

public class MovieDetails
{
    public String title;
    public String overView;
    public String image;

    public MovieDetails(String title, String overView, String image)
    {
        this.title = title;
        this.overView = overView;
        this.image = image;
    }
}