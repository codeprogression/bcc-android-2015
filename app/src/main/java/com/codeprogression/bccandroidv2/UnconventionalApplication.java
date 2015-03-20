package com.codeprogression.bccandroidv2;

import android.app.Application;

import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.squareup.picasso.Cache;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public class UnconventionalApplication extends Application {

    public static Configuration configuration;
    public static Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();

        Cache cache = new LruCache(this);
        picasso = new Picasso.Builder(this)
                .memoryCache(cache)
                .downloader(new OkHttpDownloader(this))
                .build();
    }

}
