package com.codeprogression.bccandroidv2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.codeprogression.bccandroidv2.api.TmdbApiClient;
import com.codeprogression.bccandroidv2.api.models.Movie;
import com.codeprogression.bccandroidv2.api.models.TmdbCollection;

public class MainActivity extends ActionBarActivity {

    private RecyclerView nowPlaying;
    private NowPlayingAdapter adapter;
    private ProgressBar progress;
    private TmdbApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nowPlaying = (RecyclerView) findViewById(R.id.now_playing);
        progress = (ProgressBar) findViewById(R.id.progress);
        apiClient = new TmdbApiClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
        checkConfiguration();
    }

    private void updateView() {
        nowPlaying.setLayoutManager(new GridLayoutManager(this, 2));
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
                        progress.setVisibility(View.GONE);
                        if (adapter == null) {
                            adapter = new NowPlayingAdapter(MainActivity.this, UnconventionalApplication.configuration);
                            nowPlaying.setAdapter(adapter);
                        }
                        adapter.update(result.getResults());
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
