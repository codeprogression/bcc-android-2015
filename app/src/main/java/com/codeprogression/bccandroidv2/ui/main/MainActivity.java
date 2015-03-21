package com.codeprogression.bccandroidv2.ui.main;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.codeprogression.bccandroidv2.R;
import com.codeprogression.bccandroidv2.UnconventionalApplication;
import com.codeprogression.bccandroidv2.api.TmdbApiClient;
import com.codeprogression.bccandroidv2.api.models.Movie;
import com.codeprogression.bccandroidv2.api.models.TmdbCollection;

import javax.inject.Inject;

public class MainActivity extends ActionBarActivity {

    @Inject TmdbApiClient apiClient;

    private NowPlayingView view;
    private MainActivityComponent component;

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
        checkConfiguration();
    }

    private void checkConfiguration() {
        apiClient.getConfiguration(new TmdbApiClient.Callback<Void>() {
            @Override
            public void onComplete(Void result) {
                updateNowPlaying();
            }
        });
    }

    private void updateNowPlaying() {
        apiClient.getNowPlaying(new TmdbApiClient.Callback<TmdbCollection<Movie>>() {
            @Override
            public void onComplete(final TmdbCollection<Movie> result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.bind(result);
                    }
                });
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
