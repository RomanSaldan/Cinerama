package com.lynx.cinerama.domain;

import com.lynx.cinerama.data.api.Rest;
import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.data.services.MovieService;

import org.androidannotations.annotations.EBean;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 10/26/2016.
 */

@EBean(scope = EBean.Scope.Singleton)
public class MovieRepository {

    private MovieService movieService;

    public MovieRepository() {
        movieService = Rest.getInstance().getMovieService();
    }

    private <T> Observable<T> getObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseMovieInfo> getMovieInfo(int id) {
        return getObservable(movieService.getMovieInfo(id));
    }
}
