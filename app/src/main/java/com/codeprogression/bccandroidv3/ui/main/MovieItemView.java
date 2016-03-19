package com.codeprogression.bccandroidv3.ui.main;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codeprogression.bccandroidv3.R;
import com.codeprogression.bccandroidv3.api.models.Configuration;
import com.codeprogression.bccandroidv3.api.models.Movie;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieItemView extends RelativeLayout {
  @Inject
  Picasso picasso;
  @Inject
  Configuration configuration;

  @Bind(R.id.title)
  TextView title;
  @Bind(R.id.poster)
  ImageView poster;

  private Movie movie;

  public MovieItemView(Context context, AttributeSet attrs) {
    super(context, attrs);
    ((MainActivity) context).getComponent().inject(this);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    ButterKnife.bind(this);
    if (movie == null) return;
    bind(movie);
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    ButterKnife.unbind(this);
    picasso.cancelRequest(poster);
  }

  public void bind(Movie movie) {
    this.movie = movie;
    if (title == null) return;
    title.setText(movie.getTitle());
    picasso.load(movie.getBackdropUri(configuration))
        .placeholder(R.drawable.ic_logo_tmdb_v8).into(poster);
  }
}
