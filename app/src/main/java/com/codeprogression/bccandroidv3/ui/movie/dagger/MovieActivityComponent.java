package com.codeprogression.bccandroidv3.ui.movie.dagger;

import com.codeprogression.bccandroidv3.dagger.ApplicationComponent;
import com.codeprogression.bccandroidv3.dagger.PerActivity;
import com.codeprogression.bccandroidv3.ui.movie.MovieDetailActivity;
import com.codeprogression.bccandroidv3.ui.movie.MovieDetailView;

import dagger.Component;

@PerActivity
@Component(
    dependencies = ApplicationComponent.class
)
public interface MovieActivityComponent {
  void inject(MovieDetailActivity mainActivity);

  void inject(MovieDetailView movieDetailView);
}
