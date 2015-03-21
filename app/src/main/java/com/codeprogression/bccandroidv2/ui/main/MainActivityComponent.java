package com.codeprogression.bccandroidv2.ui.main;

import com.codeprogression.bccandroidv2.UnconventionalApplication;
import com.codeprogression.bccandroidv2.ui.PerActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = UnconventionalApplication.ApplicationComponent.class
)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(MovieItemView movieItemView);
}
