package com.codeprogression.bccandroidv3.api.models;

import java.util.List;

import lombok.Getter;

/**
 * TMBD Image type definition
 */
public class Images {

  @Getter
  private String baseUrl;

  @Getter
  private String secureBaseUrl;

  @Getter
  private List<String> backdropSizes;

  @Getter
  private List<String> logoSizes;

  @Getter
  private List<String> posterSizes;

  @Getter
  private List<String> profileSizes;

  @Getter
  private List<String> stillSizes;
}
