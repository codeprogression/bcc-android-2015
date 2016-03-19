package com.codeprogression.bccandroidv3.api;

import com.codeprogression.bccandroidv3.api.models.Configuration;
import com.codeprogression.bccandroidv3.api.models.Movie;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Retrofit API definition
 */
interface TmdbApi {

  @GET("configuration")
  Observable<Configuration> getConfiguration();

  @GET("movie/now_playing")
  Observable<Movie.Collection> getMovies();

  @GET("movie/{id}")
  Observable<Movie> getMovie(@Path("id") long id);
}
