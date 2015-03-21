package com.codeprogression.bccandroidv2;

import android.app.Application;

import com.codeprogression.bccandroidv2.api.TmdbApiClient;
import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.squareup.picasso.Cache;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Component;

public class UnconventionalApplication extends Application {

    private static ApplicationComponent component;

    public static ApplicationComponent getComponent() {
        return component;
    }

    @Singleton
    @Component(modules = ApplicationModule.class)
    public interface ApplicationComponent{
        Picasso picasso();
        TmdbApiClient apiClient();
        Configuration configuration();
    }

    public void inject(){
        component = Dagger_UnconventionalApplication_ApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        inject();
    }

}
