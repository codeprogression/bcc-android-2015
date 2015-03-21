package com.codeprogression.bccandroidv2.api;

import com.codeprogression.bccandroidv2.BuildConfig;
import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.codeprogression.bccandroidv2.api.models.Movie;
import com.codeprogression.bccandroidv2.api.models.TmdbCollection;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

public class TmdbApiClient {

    private OkHttpClient client;
    private Gson gson;

    public TmdbApiClient(OkHttpClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    private Configuration configuration;

    public void getConfiguration(final Callback<Void> callback) {

        if (configuration != null) {
            callback.onComplete(null);
            return;
        }

        Request request = new Request.Builder()
                .url("http://api.themoviedb.org/3/configuration?api_key=" + BuildConfig.TMDB_API_KEY)
                .get()
                .build();

        client.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) return;

                configuration = gson.fromJson(response.body().charStream(), Configuration.class);
                callback.onComplete(null);
            }
        });
    }

    public void getNowPlaying(final Callback<TmdbCollection<Movie>> callback) {
        Request request = new Request.Builder()
                .url("http://api.themoviedb.org/3/movie/now_playing?api_key=" + BuildConfig.TMDB_API_KEY)
                .get()
                .build();
        client.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) return;
                final TmdbCollection<Movie> collection =
                        gson.fromJson(response.body().charStream(), Movie.Collection.class);
                callback.onComplete(collection);
            }
        });
    }

    public void getMovie(final long id, final Callback<Movie> callback) {
        Request request = new Request.Builder()
                .url("http://api.themoviedb.org/3/movie/" + Long.toString(id) + "?api_key=" + BuildConfig.TMDB_API_KEY)
                .get()
                .build();
        client.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) return;
                Movie movie = gson.fromJson(response.body().charStream(), Movie.class);
                callback.onComplete(movie);
            }
        });
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public interface Callback<T> {
        void onComplete(T result);
    }
}
