package com.codeprogression.bccandroidv2.api.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Configuration {
    @Expose Images images;
    @Expose List<String> changeKeys;

    public Images getImages() {
        return images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }
}
