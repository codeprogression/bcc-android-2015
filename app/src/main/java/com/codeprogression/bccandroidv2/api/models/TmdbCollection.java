package com.codeprogression.bccandroidv2.api.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class TmdbCollection<T> {
    @Expose DateRange dates;
    @Expose List<T> results;
    @Expose int page;
    @Expose int totalPages;
    @Expose int totalResults;

    public DateRange getDates() {
        return dates;
    }

    public List<T> getResults() {
        return results;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

}
