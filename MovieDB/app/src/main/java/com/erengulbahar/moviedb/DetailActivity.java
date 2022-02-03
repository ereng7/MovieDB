package com.erengulbahar.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.erengulbahar.moviedb.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity
{
    ActivityDetailBinding activityDetailBinding;
    RequestOptions option;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityDetailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = activityDetailBinding.getRoot();
        setContentView(view);

        option = new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);

        getData();
    }

    public void getData()
    {
        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        String overView = intent.getStringExtra("Overview");
        String image = intent.getStringExtra("Image");

        activityDetailBinding.titleText.setText(title);
        activityDetailBinding.overViewText.setText(overView);

        Glide.with(getApplicationContext()).load(image).apply(option).into(activityDetailBinding.imageView2);
    }
}