package com.codeprogression.bccandroidv3.ui.main;

import com.codeprogression.bccandroidv3.api.models.Movie;

import java.util.List;

/**
 * View interface for the Movies view
 */
public interface IMoviesView {

  void update(List<Movie> results);

  void showProgress(boolean showProgress);

}
