package com.codeprogression.bccandroidv2.api.models;

import com.google.gson.annotations.Expose;

public class Movie {
    public static class Collection extends TmdbCollection<Movie>{

    }

    @Expose long id;
    @Expose String title;
    @Expose String originalTitle;
    @Expose String backdropPath;
    @Expose String posterPath;
    @Expose String releaseDate;
    @Expose String overview;

    public long getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }
}
