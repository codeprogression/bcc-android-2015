package com.codeprogression.bccandroidv3.dagger;

import com.codeprogression.bccandroidv3.api.TmdbApiModule;
import com.codeprogression.bccandroidv3.api.TmdbService;
import com.codeprogression.bccandroidv3.api.models.Configuration;
import com.codeprogression.bccandroidv3.ui.LauncherActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger module for the application
 */
@Singleton
@Component(modules = {
    ApplicationModule.class,
    TmdbApiModule.class
})
public interface ApplicationComponent {

  Picasso picasso();

  TmdbService service();

  Configuration configuration();

  void inject(LauncherActivity launcherActivity);
}
