package com.codeprogression.bccandroidv2;


import com.codeprogression.bccandroidv2.api.TmdbApiService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@Module @Singleton
public class DataModule {

    @Provides @Singleton
    Gson gson(){
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides @Singleton
    OkHttpClient client(){
        return new OkHttpClient();
    }

    @Provides @Singleton
    RequestInterceptor interceptor(){
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam("api_key", BuildConfig.TMDB_API_KEY);
            }
        };
    }

    @Provides @Singleton
    TmdbApiService apiService(OkHttpClient client, Gson gson, RequestInterceptor interceptor){
        return new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3/")
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(client))
                .setRequestInterceptor(interceptor)
                .build().create(TmdbApiService.class);
    }
}
