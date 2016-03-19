package com.codeprogression.bccandroidv3.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeprogression.bccandroidv3.R;
import com.codeprogression.bccandroidv3.api.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter {
  private final LayoutInflater inflater;
  private List<Movie> movies = new ArrayList<>();

  public MoviesAdapter(Context context) {
    inflater = LayoutInflater.from(context);
  }

  public void update(List<Movie> movies) {
    this.movies = movies;
    notifyDataSetChanged();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.movie_item, parent, false);
    return new MovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((MovieViewHolder) holder).bind(movies.get(position));
  }

  @Override
  public int getItemCount() {
    return movies.size();
  }

}
