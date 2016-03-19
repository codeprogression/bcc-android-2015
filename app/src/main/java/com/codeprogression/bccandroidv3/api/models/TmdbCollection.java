package com.codeprogression.bccandroidv3.api.models;

import java.util.List;

import lombok.Getter;

public class TmdbCollection<T> {
  @Getter
  DateRange dates;

  @Getter
  List<T> results;

  @Getter
  int page;

  @Getter
  int totalPages;

  @Getter
  int totalResults;

}
