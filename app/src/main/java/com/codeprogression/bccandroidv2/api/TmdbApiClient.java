package com.codeprogression.bccandroidv2.api;

import com.codeprogression.bccandroidv2.BuildConfig;
import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.codeprogression.bccandroidv2.api.models.Movie;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class TmdbApiClient {

    private OkHttpClient client;
    private Gson gson;

    @Inject
    public TmdbApiClient(OkHttpClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    private Configuration configuration;

    public Observable<Configuration> fetchConfiguration() {

        TmdbRequestor<Configuration> requestor = new TmdbRequestor<>(client, gson);
        String url = "http://api.themoviedb.org/3/configuration?api_key=" + BuildConfig.TMDB_API_KEY;
        return Observable.create(requestor.request(url, Configuration.class));
    }

    public Observable<Movie.Collection> getNowPlaying() {
        TmdbRequestor<Movie.Collection> requestor = new TmdbRequestor<>(client, gson);

        return Observable.create(requestor.request("http://api.themoviedb.org/3/movie/now_playing?api_key="
                + BuildConfig.TMDB_API_KEY, Movie.Collection.class));

    }

    public Observable<Movie> getMovie(final long id) {
        TmdbRequestor<Movie> requestor = new TmdbRequestor<>(client, gson);
        String url = "http://api.themoviedb.org/3/movie/" + Long.toString(id) + "?api_key=" + BuildConfig.TMDB_API_KEY;
        return Observable.create(requestor.request(url, Movie.class));
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
