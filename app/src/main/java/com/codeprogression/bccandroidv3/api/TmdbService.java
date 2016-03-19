package com.codeprogression.bccandroidv3.api;

import com.codeprogression.bccandroidv3.api.models.Configuration;
import com.codeprogression.bccandroidv3.api.models.Movie;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Bridge for the Tmdb Retrofit API
 */
@Singleton
public class TmdbService {

  private TmdbApi api;
  private BehaviorSubject<Configuration> currentConfiguration = BehaviorSubject.create();

  @Inject
  public TmdbService(TmdbApi api) {
    this.api = api;
  }

  public Observable<Configuration> getConfiguration() {
    if (currentConfiguration.hasValue()){
      return Observable.just(currentConfiguration.getValue());
    }

    return api.getConfiguration()
        .subscribeOn(Schedulers.io())
        .doOnNext(new Action1<Configuration>() {
          @Override
          public void call(Configuration configuration) {
            currentConfiguration.onNext(configuration);
          }
        });
  }

  public Observable<Movie.Collection> getMovies() {
    return api.getMovies()
        .subscribeOn(Schedulers.io());
  }

  public Observable<Movie> getMovie(long id) {
    return api.getMovie(id)
        .subscribeOn(Schedulers.io());
  }

  public Configuration getCurrentConfiguration() {
    return currentConfiguration.getValue();
  }
}
