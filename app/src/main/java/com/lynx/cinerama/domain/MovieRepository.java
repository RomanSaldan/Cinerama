package com.lynx.cinerama.domain;

import com.lynx.cinerama.data.api.Rest;
import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.data.model.movies.credits.MovieCredits;
import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.data.model.movies.reviews.MovieReviews;
import com.lynx.cinerama.data.model.movies.similar.MovieSimilar;
import com.lynx.cinerama.data.model.movies.videos.MovieVideo;
import com.lynx.cinerama.data.services.MovieService;

import org.androidannotations.annotations.EBean;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 10/26/2016.
 */

@EBean(scope = EBean.Scope.Singleton)
public class MovieRepository {

    private MovieService movieService;

    private int movieID = -1;
    private Observable<ResponseMovieInfo> movieInfoObservable;

    public MovieRepository() {
        movieService = Rest.getInstance().getMovieService();
    }

    private <T> Observable<T> getObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseMovieInfo> getMovieInfo(int id) {
        if(movieInfoObservable == null || id != movieID) {
            movieID = id;
            movieInfoObservable = getObservable(movieService.getMovieInfo(id));
        }
        return movieInfoObservable;
    }

    public Observable<MovieSimilar> getMovieSimilar(int movieID, int page) {
        return getObservable(movieService.getMovieSimilar(movieID, page));
    }

    public Observable<MovieReviews> getMovieReviews(int movieID, int page) {
        return getObservable(movieService.getMovieReviews(movieID, page));
    }

    public Observable<List<ImageModel>> getMovieScenes(int movieId) {
        return getMovieInfo(movieId)
                .flatMap(movieInfo -> Observable.just(movieInfo.images.backdrops))
                .flatMap(Observable::from)
                .filter(ImageModel::getIso_639_1Valid)
                .toList();
    }


    public Observable<List<ImageModel>> getMoviePosters(int movieId) {
        return getMovieInfo(movieId)
                .flatMap(movieInfo -> Observable.just(movieInfo.images.posters))
                .flatMap(Observable::from)
                .filter(ImageModel::getIso_639_1Valid)
                .toList();
    }

    public Observable<MovieCredits> getMovieCredits(int movieId) {
        return getMovieInfo(movieId)
                .flatMap(movieInfo -> Observable.just(movieInfo.credits));
    }

    public Observable<List<MovieVideo>> getMovieVideos(int movieId) {
        return getMovieInfo(movieId)
                .flatMap(movieInfo -> Observable.just(movieInfo.videos.results))
                .flatMap(Observable::from)
                .filter(v -> v.site.equalsIgnoreCase("YouTube"))
                .toList();
    }
}
