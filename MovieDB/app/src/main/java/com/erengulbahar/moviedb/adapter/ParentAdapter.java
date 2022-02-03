package com.erengulbahar.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erengulbahar.moviedb.databinding.ParentRowBinding;
import com.erengulbahar.moviedb.model.MovieDetails;
import com.erengulbahar.moviedb.model.MovieTypes;

import java.util.ArrayList;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ParentViewHolder>
{
    ArrayList<MovieTypes> movieTypes;
    Context context;

    public ParentAdapter(ArrayList<MovieTypes> movieTypes, Context context)
    {
        this.movieTypes = movieTypes;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        ParentRowBinding parentRowBinding = ParentRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ParentViewHolder(parentRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position)
    {
        holder.parentRowBinding.genreText.setText(movieTypes.get(position).genre);
        connectChildAdapter(holder.parentRowBinding.childRecyclerView,movieTypes.get(position).movieDetails,context);
    }

    @Override
    public int getItemCount()
    {
        return movieTypes.size();
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder
    {
        ParentRowBinding parentRowBinding;

        public ParentViewHolder(ParentRowBinding parentRowBinding)
        {
            super(parentRowBinding.getRoot());
            this.parentRowBinding = parentRowBinding;
        }
    }

    private void connectChildAdapter(RecyclerView recyclerView, ArrayList<MovieDetails> movieDetails,Context context)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),RecyclerView.HORIZONTAL,false));
        ChildAdapter childAdapter = new ChildAdapter(movieDetails,context);
        recyclerView.setAdapter(childAdapter);
    }
}
