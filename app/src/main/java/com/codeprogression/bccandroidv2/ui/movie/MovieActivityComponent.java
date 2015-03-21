package com.codeprogression.bccandroidv2.ui.movie;

import com.codeprogression.bccandroidv2.UnconventionalApplication;
import com.codeprogression.bccandroidv2.ui.PerActivity;
import com.codeprogression.bccandroidv2.ui.movie.MovieActivity;
import com.codeprogression.bccandroidv2.ui.movie.MovieDetailView;

import dagger.Component;

@PerActivity
@Component(
        dependencies = UnconventionalApplication.ApplicationComponent.class
)
public interface MovieActivityComponent {
    void inject(MovieActivity mainActivity);
    void inject(MovieDetailView movieDetailView);
}
