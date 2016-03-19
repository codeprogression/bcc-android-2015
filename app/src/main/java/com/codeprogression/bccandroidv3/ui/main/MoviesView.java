package com.codeprogression.bccandroidv3.ui.main;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.codeprogression.bccandroidv3.R;
import com.codeprogression.bccandroidv3.api.models.Movie;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoviesView extends RelativeLayout implements IMoviesView {

  @Inject
  MoviesPresenter presenter;

  @Bind(R.id.recyclerView)
  RecyclerView recyclerView;

  @Bind(R.id.progress)
  ProgressBar progress;

  private MoviesAdapter adapter;

  public MoviesView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    if (isInEditMode()) return;
    MainActivity.getComponent().inject(this);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    ButterKnife.bind(this);
    configureView();
    presenter.attach(this);
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    presenter.detach();
    ButterKnife.unbind(this);
  }

  @Override
  public void update(List<Movie> results) {
    adapter.update(results);
  }

  @Override
  public void showProgress(boolean showProgress) {
    progress.setVisibility(showProgress ? VISIBLE : GONE);
  }

  private void configureView() {
    adapter = new MoviesAdapter(getContext());
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
  }


}
