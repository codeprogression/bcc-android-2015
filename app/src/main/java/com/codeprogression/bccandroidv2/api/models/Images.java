package com.codeprogression.bccandroidv2.api.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Images {

    @Expose String baseUrl;
    @Expose String secureBaseUrl;
    @Expose List<String> backdropSizes;
    @Expose List<String> logoSizes;
    @Expose List<String> posterSizes;
    @Expose List<String> profileSizes;
    @Expose List<String> stillSizes;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public List<String> getBackdropSizes() {
        return backdropSizes;
    }

    public List<String> getLogoSizes() {
        return logoSizes;
    }

    public List<String> getPosterSizes() {
        return posterSizes;
    }

    public List<String> getProfileSizes() {
        return profileSizes;
    }

    public List<String> getStillSizes() {
        return stillSizes;
    }
}
