package com.codeprogression.bccandroidv3.ui.movie;

import android.databinding.Observable;

import rx.functions.Action0;

/**
 * Unit test helpers
 */
public final class TestHelpers {

  private TestHelpers() {

  }

  public static void onViewModelChanged(final Observable viewModel, final Action0 action) {
    viewModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
      @Override
      public void onPropertyChanged(Observable sender, int propertyId) {
        action.call();
        viewModel.removeOnPropertyChangedCallback(this);
      }
    });
  }

}
