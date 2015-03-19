package com.codeprogression.bccandroidv2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.codeprogression.bccandroidv2.api.models.Movie;
import com.codeprogression.bccandroidv2.api.models.TmdbCollection;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends ActionBarActivity {

    private RecyclerView nowPlaying;
    private NowPlayingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nowPlaying = (RecyclerView) findViewById(R.id.now_playing);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
        updateNowPlaying();
    }

    private void updateView() {
        nowPlaying.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NowPlayingAdapter(this);
        nowPlaying.setAdapter(adapter);
    }

    private void updateNowPlaying() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://api.themoviedb.org/3/movie/now_playing?api_key="
                            + BuildConfig.TMDB_API_KEY);
                    URLConnection connection = url.openConnection();
                    BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
                    Gson gson = new GsonBuilder()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .create();
                    final TmdbCollection<Movie> collection =
                            gson.fromJson(new InputStreamReader(inputStream), Movie.Collection.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            adapter.update(collection.getResults());
                        }
                    });

                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
