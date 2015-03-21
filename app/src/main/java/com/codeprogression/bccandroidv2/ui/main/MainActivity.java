package com.codeprogression.bccandroidv2.ui.main;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codeprogression.bccandroidv2.R;
import com.codeprogression.bccandroidv2.UnconventionalApplication;
import com.codeprogression.bccandroidv2.api.TmdbApiClient;
import com.codeprogression.bccandroidv2.api.models.Movie;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends ActionBarActivity {

    @Inject TmdbApiClient apiClient;

    private NowPlayingView view;
    private MainActivityComponent component;
    private Subscription subscription;

    public MainActivityComponent getComponent() {
        return component;
    }

    private void inject() {
        component = Dagger_MainActivityComponent.builder()
                .applicationComponent(UnconventionalApplication.getComponent())
                .build();
        component.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();

        setContentView(R.layout.activity_main);
        view = (NowPlayingView) findViewById(R.id.now_playing_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateNowPlaying();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    private void updateNowPlaying() {
        subscription = apiClient.getNowPlaying()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movie.Collection>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Movie.Collection collection) {
                        view.bind(collection);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
