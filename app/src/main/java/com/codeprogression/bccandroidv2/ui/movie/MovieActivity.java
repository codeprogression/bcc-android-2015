package com.codeprogression.bccandroidv2.ui.movie;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.codeprogression.bccandroidv2.R;
import com.codeprogression.bccandroidv2.UnconventionalApplication;
import com.codeprogression.bccandroidv2.api.TmdbApiClient;
import com.codeprogression.bccandroidv2.api.models.Movie;

import javax.inject.Inject;

public class MovieActivity extends ActionBarActivity {
    @Inject TmdbApiClient apiClient;

    private long id;
    private Movie movie;
    private MovieDetailView view;

    private MovieActivityComponent component;

    public MovieActivityComponent getComponent() {
        return component;
    }

    private void inject() {
        component = Dagger_MovieActivityComponent.builder()
                .applicationComponent(UnconventionalApplication.getComponent())
                .build();
        component.inject(this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();

        setContentView(R.layout.movie_detail);

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
