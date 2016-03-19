package com.codeprogression.bccandroidv3.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.codeprogression.bccandroidv3.R;
import com.codeprogression.bccandroidv3.UnconventionalApplication;
import com.codeprogression.bccandroidv3.api.TmdbService;
import com.codeprogression.bccandroidv3.ui.main.MainActivity;

import javax.inject.Inject;

import rx.Completable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class LauncherActivity extends AppCompatActivity {

  @Inject
  TmdbService service;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    UnconventionalApplication.getComponent().inject(this);

    setContentView(R.layout.activity_launcher);

    service.getConfiguration()
        .toCompletable()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Completable.CompletableSubscriber() {
          @Override
          public void onCompleted() {
            MainActivity.startActivity(LauncherActivity.this);
          }

          @Override
          public void onError(Throwable e) {
            Timber.e(e, "Error retrieving config");
            showConfigurationError();
          }

          @Override
          public void onSubscribe(Subscription d) {

          }
        });
  }

  private void showConfigurationError() {
    new AlertDialog.Builder(LauncherActivity.this)
        .setTitle("Cannot continue!")
        .setMessage("Unable to configure application. Try again later.")
        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            finish();
          }
        })
        .create().show();
  }
}
