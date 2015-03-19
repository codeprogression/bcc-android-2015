package com.codeprogression.bccandroidv2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.codeprogression.bccandroidv2.api.models.Movie;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private List<Movie> movies = new ArrayList<>();
    private Configuration configuration;

    public NowPlayingAdapter(Context context, Configuration configuration) {
        this.configuration = configuration;
        inflater = LayoutInflater.from(context);
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

    private class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final ImageView poster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            poster = (ImageView) itemView.findViewById(R.id.poster);
        }

        public void bind(final Movie movie) {
            title.setText(movie.getTitle());
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        String baseUrl = configuration.getImages().getBaseUrl();
                        String posterSize = configuration.getImages().getBackdropSizes().get(1);
                        String posterPath = movie.getBackdropPath();
                        final URL uri = new URL(String.format("%s%s%s", baseUrl, posterSize, posterPath));
                        final Bitmap bm = BitmapFactory.decodeStream(uri.openConnection().getInputStream());
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {

                                poster.setImageBitmap(bm);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
