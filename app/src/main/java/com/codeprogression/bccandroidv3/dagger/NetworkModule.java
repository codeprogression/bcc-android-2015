package com.codeprogression.bccandroidv3.dagger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Dagger module that defines networking dependencies
 */
@Module
public class NetworkModule {

  @Provides
  @Singleton
  GsonBuilder gsonBuilder() {
    return new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
  }

  @Provides
  @Singleton
  OkHttpClient.Builder clientBuilder() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return new OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor);
  }
}
