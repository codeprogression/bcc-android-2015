package com.codeprogression.bccandroidv2.api.models;

import com.codeprogression.bccandroidv2.UnconventionalApplication;
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

    public String getPosterUri(Configuration configuration) {
        String baseUrl = configuration.getImages().getBaseUrl();
        String posterSize = configuration.getImages().getPosterSizes().get(6);
        String uri = String.format("%s%s%s", baseUrl, posterSize, posterPath);
        return uri;
    }

    public String getBackdropUri(Configuration configuration) {
        String baseUrl = configuration.getImages().getBaseUrl();
        String backdropSize = configuration.getImages().getBackdropSizes().get(2);
        String uri = String.format("%s%s%s", baseUrl, backdropSize, backdropPath);
        return uri;
    }
}
