package com.codeprogression.bccandroidv3.ui.main;

import com.codeprogression.bccandroidv3.api.TmdbService;
import com.codeprogression.bccandroidv3.api.models.Movie;
import com.codeprogression.bccandroidv3.dagger.PerActivity;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Presenter to mediate an IMoviesView
 */
@PerActivity
public class MoviesPresenter {
  private IMoviesView view;
  private TmdbService service;
  private Subscription subscription;

  @Inject
  public MoviesPresenter(TmdbService service) {
    this.service = service;
  }

  public void attach(IMoviesView view) {
    this.view = view;
    load();
  }

  public void detach() {
    this.view = null;
    if (subscription != null){
      subscription.unsubscribe();
    }
  }

  private void load() {
    subscription = service.getMovies()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Movie.Collection>() {
          @Override
          public void onStart() {
            super.onStart();
            if (view == null) return;
            view.showProgress(true);
          }

          @Override
          public void onCompleted() {
            if (view == null) return;
            view.showProgress(false);
          }

          @Override
          public void onError(Throwable e) {
            Timber.e(e, "Error while retrieving movies");
            if (view == null) return;
            view.showProgress(false);
          }

          @Override
          public void onNext(Movie.Collection collection) {
            if (view == null) return;
            view.update((List<Movie>) collection);
          }
        });
  }

}
