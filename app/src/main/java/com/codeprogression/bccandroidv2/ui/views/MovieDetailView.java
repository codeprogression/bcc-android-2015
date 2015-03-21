package com.codeprogression.bccandroidv2.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codeprogression.bccandroidv2.R;
import com.codeprogression.bccandroidv2.UnconventionalApplication;
import com.codeprogression.bccandroidv2.api.models.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailView extends RelativeLayout {

    private Picasso picasso = UnconventionalApplication.picasso;

    private ProgressBar progress;
    private ImageView poster;
    private TextView title;
    private TextView overview;

    public MovieDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
    }

    public void bind(Movie movie){
        progress.setVisibility(View.GONE);
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        String posterUri = movie.getPosterUri(UnconventionalApplication.configuration);
        picasso.load(posterUri).into(poster);
    }
}
