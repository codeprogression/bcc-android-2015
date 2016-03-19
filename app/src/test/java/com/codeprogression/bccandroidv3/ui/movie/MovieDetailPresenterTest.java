package com.codeprogression.bccandroidv3.ui.movie;

import android.support.annotation.NonNull;

import com.codeprogression.bccandroidv3.api.TmdbService;
import com.codeprogression.bccandroidv3.api.models.Configuration;
import com.codeprogression.bccandroidv3.api.models.Movie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.functions.Action0;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by richard on 3/19/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieDetailPresenterTest {

  @Mock
  Configuration configuration;

  @Mock
  TmdbService service;
  private MovieDetailPresenter presenter;

  @Before
  public void setUp() throws Exception {
    presenter = new MovieDetailPresenter(configuration, service);
  }

  @Test
  public void testAttach() throws Exception {
    presenter.attach(new MovieDetailView.ViewModel());

    verify(service, never()).getMovie(anyLong());
  }

  @Test
  public void testAttachAfterMediaIdSet() throws Exception {
    when(service.getMovie(anyLong())).thenReturn(Observable.<Movie>empty());

    presenter.attach(new MovieDetailView.ViewModel());
    presenter.setMovieId(1);

    verify(service, times(1)).getMovie(1);

  }

  @Test
  public void testViewModel() throws Exception {

    when(service.getMovie(anyLong())).thenReturn(Observable.just(getMovie()));

    final MovieDetailView.ViewModel viewModel = new MovieDetailView.ViewModel();
    presenter.attach(viewModel);
    presenter.setMovieId(1);

    TestHelpers.onViewModelChanged(viewModel, new Action0() {
      @Override
      public void call() {
        assertThat(viewModel.title.get().equals("title"));
        assertThat(viewModel.overview.get().equals("overview"));
        assertThat(viewModel.overview.get().equals("/title.png"));
      }
    });

  }

  @NonNull
  private Movie getMovie() {
    return new Movie() {
      @Override
      public String getTitle() {
        return "title";
      }

      @Override
      public String getOverview() {
        return "overview";
      }

      @Override
      public String getPosterUri(Configuration configuration) {
        return "/title.png";
      }
    };
  }

}