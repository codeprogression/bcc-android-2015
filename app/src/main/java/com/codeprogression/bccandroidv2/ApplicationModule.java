package com.codeprogression.bccandroidv2;

import android.app.Application;
import android.content.Context;

import com.codeprogression.bccandroidv2.api.TmdbApiClient;
import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.squareup.picasso.Cache;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
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
                .downloader(new OkHttpDownloader(context))
                .build();
    }

    @Provides
    @Singleton
    TmdbApiClient apiClient() {
        return new TmdbApiClient();
    }

    @Provides
    @Singleton
    Configuration configuration(final TmdbApiClient apiClient) {
        return apiClient.getConfiguration();
    }
}