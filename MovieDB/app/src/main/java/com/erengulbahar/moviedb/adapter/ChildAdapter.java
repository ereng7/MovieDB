package com.erengulbahar.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.erengulbahar.moviedb.DetailActivity;
import com.erengulbahar.moviedb.MainActivity;
import com.erengulbahar.moviedb.R;
import com.erengulbahar.moviedb.databinding.ChildRowBinding;
import com.erengulbahar.moviedb.model.MovieDetails;

import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder>
{
    ArrayList<MovieDetails> movieDetails;
    Context context;
    RequestOptions option;

    public ChildAdapter(ArrayList<MovieDetails> movieDetails, Context context)
    {
        this.movieDetails = movieDetails;
        this.context = context;

        option = new RequestOptions().autoClone().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        ChildRowBinding childRowBinding = ChildRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ChildViewHolder(childRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position)
    {
        //Load image here.
        Glide.with(context).load(movieDetails.get(position).image).apply(option).into(holder.childRowBinding.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("Title",movieDetails.get(position).title);
                intent.putExtra("Overview",movieDetails.get(position).overView);
                intent.putExtra("Image",movieDetails.get(position).image);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return movieDetails.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder
    {
        ChildRowBinding childRowBinding;

        public ChildViewHolder(ChildRowBinding childRowBinding)
        {
            super(childRowBinding.getRoot());
            this.childRowBinding = childRowBinding;
        }
    }
}