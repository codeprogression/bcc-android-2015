package com.codeprogression.bccandroidv3.ui.main.dagger;

import com.codeprogression.bccandroidv3.dagger.ApplicationComponent;
import com.codeprogression.bccandroidv3.dagger.PerActivity;
import com.codeprogression.bccandroidv3.ui.main.MainActivity;
import com.codeprogression.bccandroidv3.ui.main.MovieItemView;
import com.codeprogression.bccandroidv3.ui.main.MoviesView;

import dagger.Component;

@PerActivity
@Component(
    dependencies = ApplicationComponent.class
)
public interface MainActivityComponent {

  void inject(MoviesView moviesView);

  void inject(MovieItemView movieItemView);
}
