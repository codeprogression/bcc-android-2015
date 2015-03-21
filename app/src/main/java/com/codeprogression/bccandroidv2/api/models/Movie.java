package com.codeprogression.bccandroidv2.api.models;

import com.google.gson.annotations.Expose;

import lombok.Getter;

public class Movie {

    public static class Collection extends TmdbCollection<Movie>{

    }

    @Expose @Getter long id;
    @Expose @Getter String title;
    @Expose String originalTitle;
    @Expose String backdropPath;
    @Expose String posterPath;
    @Expose String releaseDate;
    @Expose @Getter String overview;

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
