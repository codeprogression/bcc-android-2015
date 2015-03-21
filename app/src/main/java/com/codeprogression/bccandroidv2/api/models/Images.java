package com.codeprogression.bccandroidv2.api.models;

import com.google.gson.annotations.Expose;

import java.util.List;

import lombok.Getter;

public class Images {

    @Expose @Getter String baseUrl;
    @Expose @Getter String secureBaseUrl;
    @Expose @Getter List<String> backdropSizes;
    @Expose @Getter List<String> logoSizes;
    @Expose @Getter List<String> posterSizes;
    @Expose @Getter List<String> profileSizes;
    @Expose @Getter List<String> stillSizes;

}
