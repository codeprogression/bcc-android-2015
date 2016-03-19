package com.codeprogression.bccandroidv3.ui.movie;

import com.codeprogression.bccandroidv3.api.TmdbService;
import com.codeprogression.bccandroidv3.api.models.Configuration;
import com.codeprogression.bccandroidv3.api.models.Movie;
import com.codeprogression.bccandroidv3.dagger.PerActivity;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Presenter to mediate a MovieDetailViewModel
 */
@PerActivity
public class MovieDetailPresenter {

  private final Configuration configuration;
  private final TmdbService service;
  private long movieId;
  private MovieDetailView.ViewModel viewModel;
  private Subscription subscription;

  @Inject
  public MovieDetailPresenter(final Configuration configuration, final TmdbService service) {
    this.configuration = configuration;
    this.service = service;
  }

  public void attach(final MovieDetailView.ViewModel viewModel) {
    this.viewModel = viewModel;
    load();
  }

  public void detach() {
    viewModel = null;
    if (subscription != null) {
      subscription.unsubscribe();
    }
  }

  private void load() {
    if (movieId == 0) {
      return;
    }
    getMovieDetails();
  }

  private void getMovieDetails() {
    subscription = service.getMovie(movieId)
          .subscribe(new Subscriber<Movie>() {
          @Override
          public void onStart() {
            if (viewModel == null) return;
            viewModel.isLoading.set(true);
          }

          @Override
          public void onCompleted() {

            if (viewModel == null) return;
            viewModel.isLoading.set(false);
          }

          @Override
          public void onError(Throwable e) {
            Timber.e(e.getMessage(), e);
            if (viewModel == null) return;
            viewModel.isLoading.set(false);
          }

          @Override
          public void onNext(Movie result) {
            if (viewModel == null) return;

            viewModel.title.set(result.getTitle());
            viewModel.overview.set(result.getOverview());
            viewModel.imageUrl.set(result.getPosterUri(configuration));
          }
        });
  }

  public void setMovieId(final long movieId) {
    this.movieId = movieId;
    load();
  }
}
