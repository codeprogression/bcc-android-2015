package com.codeprogression.bccandroidv2.ui.movie;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.codeprogression.bccandroidv2.R;
import com.codeprogression.bccandroidv2.UnconventionalApplication;
import com.codeprogression.bccandroidv2.api.TmdbApiClient;
import com.codeprogression.bccandroidv2.api.models.Movie;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MovieActivity extends ActionBarActivity {
    @Inject TmdbApiClient apiClient;
    @InjectView(R.id.detail_view) MovieDetailView view;
    @InjectExtra("id") long id;

    private Movie movie;

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

        ButterKnife.inject(this);
        Dart.inject(this);
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
            apiClient.getMovie(id)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Movie>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Timber.e(e.getMessage(), e);
                        }

                        @Override
                        public void onNext(Movie result) {
                            movie = result;
                            view.bind(result);
                        }
                    });
        }
    }
}