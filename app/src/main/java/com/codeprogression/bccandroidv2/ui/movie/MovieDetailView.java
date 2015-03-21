package com.codeprogression.bccandroidv2.ui.movie;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codeprogression.bccandroidv2.R;
import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.codeprogression.bccandroidv2.api.models.Movie;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MovieDetailView extends RelativeLayout {

    @Inject Picasso picasso;
    @Inject Configuration configuration;

    @InjectView(R.id.progress) ProgressBar progress;
    @InjectView(R.id.poster) ImageView poster;
    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.overview) TextView overview;

    public MovieDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ((MovieActivity)context).getComponent().inject(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ButterKnife.inject(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.reset(this);
        picasso.cancelRequest(poster);
    }

    public void bind(Movie movie){
        progress.setVisibility(View.GONE);
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        String posterUri = movie.getPosterUri(configuration);
        picasso.load(posterUri).into(poster);
    }
}
