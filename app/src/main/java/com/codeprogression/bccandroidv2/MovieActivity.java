package com.codeprogression.bccandroidv2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMovieDetails();
    }

    private void getMovieDetails() {

        if (movie != null){
            updateView();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        URL url = new URL("http://api.themoviedb.org/3/movie/" + Long.toString(id) + "?api_key="
                                + BuildConfig.TMDB_API_KEY);
                        URLConnection connection = url.openConnection();
                        BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
                        Gson gson = new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create();
                        movie =
                                gson.fromJson(new InputStreamReader(inputStream), Movie.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.setVisibility(View.GONE);
                                updateView();
                            }
                        });

                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private void updateView() {

        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        String baseUrl = UnconventionalApplication.configuration.getImages().getBaseUrl();
        String posterSize = UnconventionalApplication.configuration.getImages().getPosterSizes().get(6);
        String posterPath = movie.getPosterPath();
        String uri = String.format("%s%s%s", baseUrl, posterSize, posterPath);
        picasso.load(uri).into(poster);
    }
}
