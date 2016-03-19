package com.codeprogression.bccandroidv3;

import android.app.Application;

import com.codeprogression.bccandroidv3.dagger.ApplicationComponent;
import com.codeprogression.bccandroidv3.dagger.ApplicationModule;
import com.codeprogression.bccandroidv3.dagger.DaggerApplicationComponent;

import timber.log.Timber;

public class UnconventionalApplication extends Application {

  private static ApplicationComponent component;

  public static ApplicationComponent getComponent() {
    return component;
  }

  public void createComponent() {
    component = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }

  @Override
  public void onCreate() {
    super.onCreate();
    createComponent();
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
//        } else {
      // Timber.plant(new CrashlyticsTree());
    }
  }

}
