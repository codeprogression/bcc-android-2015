package com.codeprogression.bccandroidv2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codeprogression.bccandroidv2.api.TmdbApiClient;
import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.codeprogression.bccandroidv2.api.models.Movie;
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
    private ProgressBar progress;
    private Movie movie;
    private Picasso picasso = UnconventionalApplication.picasso;
    private ImageView poster;
    private TextView title;
    private TextView overview;
    private TmdbApiClient apiClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id");

        progress = (ProgressBar) findViewById(R.id.progress);
        poster = (ImageView) findViewById(R.id.poster);
        title = (TextView) findViewById(R.id.title);
        overview = (TextView) findViewById(R.id.overview);
        apiClient = new TmdbApiClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMovieDetails();
    }

    private void getMovieDetails() {
        if (movie != null) {
            updateView();
        } else {
            apiClient.getMovie(id, new TmdbApiClient.Callback<Movie>() {
                @Override
                public void onComplete(Movie result) {
                    movie = result;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progress.setVisibility(View.GONE);
                            updateView();
                        }
                    });
                }
            });
        }
    }

    private void updateView() {

        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        String posterUri = movie.getPosterUri(UnconventionalApplication.configuration);
        picasso.load(posterUri).into(poster);
    }
}
