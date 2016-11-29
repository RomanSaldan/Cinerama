package com.lynx.cinerama.data.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;
import com.lynx.cinerama.data.model.search.ResponseMultiSearch;
import com.lynx.cinerama.data.services.ActorService;
import com.lynx.cinerama.data.services.MovieService;
import com.lynx.cinerama.data.services.SearchService;
import com.lynx.cinerama.presentation.utils.Constants;
import com.lynx.cinerama.presentation.utils.SearchJsonDeserializer;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
    private Retrofit searchRetrofit;
    private OkHttpClient client;

    private MovieService movieService;
    private ActorService actorService;
    private SearchService searchService;

    private Rest() {
        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(chain -> {
                    HttpUrl original = chain.request().url();
                    HttpUrl url = original.newBuilder()
                            .addQueryParameter("api_key", Constants.API_KEY_TMDB)
                            .build();
                    Request request = chain.request().newBuilder().url(url).build();
                    return chain.proceed(request);
                })
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

    public ActorService getActorService() {
        return actorService == null ? retrofit.create(ActorService.class) : actorService;
    }

    public SearchService getSearchService() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ResponseMultiSearch.class, new SearchJsonDeserializer());

        searchRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build();
        return searchService == null ? searchRetrofit.create(SearchService.class) : searchService;
    }
}
