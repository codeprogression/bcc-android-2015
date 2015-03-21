package com.codeprogression.bccandroidv2.ui.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codeprogression.bccandroidv2.api.models.Movie;
import com.codeprogression.bccandroidv2.ui.main.MovieItemView;
import com.codeprogression.bccandroidv2.ui.movie.MovieActivity;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private Movie movie;

    public MovieViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public void bind(final Movie movie) {
        this.movie = movie;
        ((MovieItemView)itemView).bind(movie);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), MovieActivity.class);
        intent.putExtra("id", movie.getId());
        intent.putExtra("title", movie.getTitle());
        v.getContext().startActivity(intent);
    }
}
