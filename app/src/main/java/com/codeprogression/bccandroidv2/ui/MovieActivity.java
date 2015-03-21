package com.codeprogression.bccandroidv2.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codeprogression.bccandroidv2.R;
import com.codeprogression.bccandroidv2.UnconventionalApplication;
import com.codeprogression.bccandroidv2.api.TmdbApiClient;
import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.codeprogression.bccandroidv2.api.models.Movie;
import com.codeprogression.bccandroidv2.ui.views.MovieDetailView;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MovieActivity extends ActionBarActivity {
    private long id;
    private Movie movie;
    private TmdbApiClient apiClient;
    private MovieDetailView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        apiClient = new TmdbApiClient();

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id");

        view = (MovieDetailView) findViewById(R.id.detail_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMovieDetails();
    }

    private void getMovieDetails() {
        if (movie != null) {
            view.bind(movie);
        } else {
            apiClient.getMovie(id, new TmdbApiClient.Callback<Movie>() {
                @Override
                public void onComplete(final Movie result) {
                    movie = result;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.bind(result);
                        }
                    });
                }
            });
        }
    }
}
