package com.codeprogression.bccandroidv3.ui.movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codeprogression.bccandroidv3.R;
import com.codeprogression.bccandroidv3.UnconventionalApplication;
import com.codeprogression.bccandroidv3.ui.movie.dagger.DaggerMovieActivityComponent;
import com.codeprogression.bccandroidv3.ui.movie.dagger.MovieActivityComponent;

import javax.inject.Inject;

public class MovieDetailActivity extends AppCompatActivity {
  @Inject MovieDetailPresenter presenter;

  private static MovieActivityComponent component;
  public static MovieActivityComponent getComponent() {
    return component;
  }

  private void inject() {
    component = DaggerMovieActivityComponent.builder()
        .applicationComponent(UnconventionalApplication.getComponent())
        .build();
    component.inject(this);
  }

  public static void startActivity(Context context, long id) {
    Intent intent = new Intent(context, MovieDetailActivity.class);
    intent.putExtra("id", id);
    context.startActivity(intent);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    inject();

    setContentView(R.layout.movie_detail);
    presenter.setMovieId(getIntent().getLongExtra("id", 0));
  }
}