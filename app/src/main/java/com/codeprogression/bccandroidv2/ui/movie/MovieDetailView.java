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

public class MovieDetailView extends RelativeLayout {

    @Inject Picasso picasso;
    @Inject Configuration configuration;

    private ProgressBar progress;
    private ImageView poster;
    private TextView title;
    private TextView overview;

    public MovieDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ((MovieActivity)context).getComponent().inject(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        progress = (ProgressBar) findViewById(R.id.progress);
        poster = (ImageView) findViewById(R.id.poster);
        title = (TextView) findViewById(R.id.title);
        overview = (TextView) findViewById(R.id.overview);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
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
