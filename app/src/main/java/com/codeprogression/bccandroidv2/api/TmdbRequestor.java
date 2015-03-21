package com.codeprogression.bccandroidv2.api;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

public class TmdbRequestor<T> {

    private final OkHttpClient client;
    private final Gson gson;

    public TmdbRequestor(OkHttpClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    public Observable.OnSubscribe<T> request(final String url, final Class<? super T> clazz) {
        return new Observable.OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {

                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            subscriber.onError(new Exception(response.message()));
                            return;
                        }
                        final T type =
                                (T) gson.fromJson(response.body().charStream(), clazz);
                        subscriber.onNext(type);
                        subscriber.onCompleted();
                    }
                });
            }
        };
    }
}
