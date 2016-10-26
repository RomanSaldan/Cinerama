package com.lynx.cinerama.data.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.lynx.cinerama.data.services.MovieService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.lynx.cinerama.presentation.utils.Constants.BASE_URL;

/**
 * Created by Lynx on 10/26/2016.
 */

public class Rest {

    private static Rest ourInstance;
    private Retrofit retrofit;

    private MovieService movieService;

    private Rest() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build();
    }

    public static Rest getInstance() {
        return ourInstance == null ? new Rest() : ourInstance;
    }

    public MovieService getMovieService() {
        return movieService == null ? retrofit.create(MovieService.class) : movieService;
    }
}
