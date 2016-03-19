package com.codeprogression.bccandroidv3.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codeprogression.bccandroidv3.api.models.Movie;
import com.codeprogression.bccandroidv3.ui.movie.MovieDetailActivity;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  private Movie movie;

  public MovieViewHolder(View itemView) {
    super(itemView);
    itemView.setOnClickListener(this);
  }

  public void bind(final Movie movie) {
    this.movie = movie;
    ((MovieItemView) itemView).bind(movie);
  }

  @Override
  public void onClick(View v) {
    MovieDetailActivity.startActivity(v.getContext(), movie.getId());
  }
}
