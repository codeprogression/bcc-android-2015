package com.codeprogression.bccandroidv3.api;


import android.support.annotation.NonNull;

import com.codeprogression.bccandroidv3.BuildConfig;
import com.codeprogression.bccandroidv3.dagger.NetworkModule;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger module for the TMDB API (includes network module)
 *
 */
@Module(includes = NetworkModule.class)
public class TmdbApiModule {

  @Provides
  @Singleton
  TmdbApi apiService(OkHttpClient.Builder client, GsonBuilder gson) {

    client.addInterceptor(getApiKeyInterceptor());

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create(gson.create()))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();

    return retrofit.create(TmdbApi.class);
  }

  @NonNull
  private Interceptor getApiKeyInterceptor() {
    return new Interceptor() {
      @Override
      public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY).build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
      }
    };
  }
}
