package com.codeprogression.bccandroidv2.ui.main;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codeprogression.bccandroidv2.R;
import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.codeprogression.bccandroidv2.api.models.Movie;
import com.codeprogression.bccandroidv2.ui.main.MainActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class MovieItemView extends RelativeLayout {
    @Inject Picasso picasso;
    @Inject Configuration configuration;

    private TextView title;
    private ImageView poster;
    private Movie movie;

    public MovieItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ((MainActivity)context).getComponent().inject(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        title = (TextView) findViewById(R.id.title);
        poster = (ImageView) findViewById(R.id.poster);
        if (movie == null) return;
        bind(movie);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        picasso.cancelRequest(poster);
    }

    public void bind(Movie movie){
        this.movie = movie;
        if (title == null) return;
        title.setText(movie.getTitle());
        picasso.load(movie.getBackdropUri(configuration)).placeholder(R.drawable.ic_logo_tmdb_v8).into(poster);
    }
}
