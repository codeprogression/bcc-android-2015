package com.codeprogression.bccandroidv2.api;

import com.codeprogression.bccandroidv2.BuildConfig;
import com.codeprogression.bccandroidv2.UnconventionalApplication;
import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.codeprogression.bccandroidv2.api.models.Movie;
import com.codeprogression.bccandroidv2.api.models.TmdbCollection;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TmdbApiClient {

    public void getConfiguration(final Callback<Void> callback) {

        if (UnconventionalApplication.configuration != null){
            callback.onComplete(null);
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://api.themoviedb.org/3/configuration?api_key="
                            + BuildConfig.TMDB_API_KEY);
                    URLConnection connection = url.openConnection();
                    BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
                    Gson gson = new GsonBuilder()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .create();
                    UnconventionalApplication.configuration =
                            gson.fromJson(new InputStreamReader(inputStream), Configuration.class);
                    callback.onComplete(null);

                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public void getNowPlaying(final Callback<TmdbCollection<Movie>> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://api.themoviedb.org/3/movie/now_playing?api_key="
                            + BuildConfig.TMDB_API_KEY);
                    URLConnection connection = url.openConnection();
                    BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
                    Gson gson = new GsonBuilder()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .create();
                    final TmdbCollection<Movie> collection =
                            gson.fromJson(new InputStreamReader(inputStream), Movie.Collection.class);
                    callback.onComplete(collection);

                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getMovie(final long id, final Callback<Movie> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            URL url = new URL("http://api.themoviedb.org/3/movie/" + Long.toString(id) + "?api_key="
                                    + BuildConfig.TMDB_API_KEY);
                            URLConnection connection = url.openConnection();
                            BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
                            Gson gson = new GsonBuilder()
                                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                    .create();
                            Movie movie = gson.fromJson(new InputStreamReader(inputStream), Movie.class);
                            callback.onComplete(movie);

                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }).start();

    }

    public interface Callback<T> {
        void onComplete(T result);
    }
}
