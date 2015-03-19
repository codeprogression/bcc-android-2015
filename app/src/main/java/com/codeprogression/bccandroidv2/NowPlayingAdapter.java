package com.codeprogression.bccandroidv2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codeprogression.bccandroidv2.api.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private List<Movie> movies = new ArrayList<>();

    public NowPlayingAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void update(List<Movie> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder)holder).bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;

        public MovieViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
        }

        public void bind(Movie movie) {
            title.setText(movie.getTitle());
        }
    }
}
