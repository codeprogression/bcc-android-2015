package com.codeprogression.bccandroidv3.ui.movie;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.codeprogression.bccandroidv3.databinding.MovieDetailBinding;

import javax.inject.Inject;

public class MovieDetailView extends RelativeLayout {

  @Inject
  MovieDetailPresenter presenter;

  private MovieDetailBinding binding;

  public MovieDetailView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    if (isInEditMode()) return;
    MovieDetailActivity.getComponent().inject(this);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    ViewModel viewModel = new ViewModel();
    binding = DataBindingUtil.bind(this);
    binding.setViewModel(viewModel);
    presenter.attach(viewModel);
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    presenter.detach();
    binding.unbind();
  }

  public static class ViewModel extends BaseObservable {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> overview = new ObservableField<>();
    public final ObservableField<String> imageUrl = new ObservableField<>();
    public final ObservableBoolean isLoading = new ObservableBoolean();
  }
}
