package com.codeprogression.bccandroidv3.dagger;

import android.app.Application;
import android.content.Context;

import com.codeprogression.bccandroidv3.api.TmdbApiModule;
import com.codeprogression.bccandroidv3.api.TmdbService;
import com.codeprogression.bccandroidv3.api.models.Configuration;
import com.squareup.picasso.Cache;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that defines application-wide dependencies
 */
@Singleton
@Module(includes = TmdbApiModule.class)
public class ApplicationModule {

  private Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  Context applicationContext() {
    return application;
  }

  @Provides
  @Singleton
  Picasso picasso(Context context) {
    Cache cache = new LruCache(context);
    return new Picasso.Builder(context)
        .memoryCache(cache)
        .downloader(new OkHttp3Downloader(context))
        .build();
  }

  @Provides
  @Singleton
  Configuration configuration(TmdbService service) {
    return service.getCurrentConfiguration();
  }
}
