package com.codeprogression.bccandroidv3.api.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(of = "id")
@ToString
public class Movie {

  public static class Collection extends TmdbCollection<Movie> {

  }

  @Getter
  long id;

  @Getter
  String title;

  @Getter
  String originalTitle;

  @Getter
  String backdropPath;

  @Getter
  String posterPath;

  @Getter
  String releaseDate;

  @Getter
  String overview;

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
