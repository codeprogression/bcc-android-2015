package com.codeprogression.bccandroidv2.api.models;

import com.google.gson.annotations.Expose;

public class DateRange {
    @Expose String minimum;
    @Expose String maximum;

    public String getMinimum() {
        return minimum;
    }

    public String getMaximum() {
        return maximum;
    }
}
