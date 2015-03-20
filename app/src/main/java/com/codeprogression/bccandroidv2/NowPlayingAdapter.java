package com.codeprogression.bccandroidv2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.codeprogression.bccandroidv2.api.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private final Picasso picasso;
    private List<Movie> movies = new ArrayList<>();
    private Configuration configuration;

    public NowPlayingAdapter(Context context, Configuration configuration) {
        this.configuration = configuration;
        inflater = LayoutInflater.from(context);
        picasso = Picasso.with(context);
    }

    public void update(List<Movie> movies) {
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
        ((MovieViewHolder) holder).bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView title;
        private final ImageView poster;
        private Movie movie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            poster = (ImageView) itemView.findViewById(R.id.poster);
            itemView.setOnClickListener(this);
        }

        public void bind(final Movie movie) {
            this.movie = movie;
            title.setText(movie.getTitle());

            String baseUrl = configuration.getImages().getBaseUrl();
            String posterSize = configuration.getImages().getBackdropSizes().get(1);
            String posterPath = movie.getBackdropPath();
            String uri = String.format("%s%s%s", baseUrl, posterSize, posterPath);
            picasso.load(uri).placeholder(R.drawable.ic_logo_tmdb_v8).into(poster);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), MovieActivity.class);
            intent.putExtra("id", movie.getId());
            intent.putExtra("title", movie.getTitle());
            v.getContext().startActivity(intent);
        }
    }
}
