package com.codeprogression.bccandroidv2.api;

import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.codeprogression.bccandroidv2.api.models.Movie;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface TmdbApiService {

    @GET("/configuration")
    public Observable<Configuration> getConfiguration();

    @GET("/movie/now_playing")
    public Observable<Movie.Collection> getNowPlaying();

    @GET("/movie/{id}")
    public Observable<Movie> getMovie(@Path("id") long id);

}
