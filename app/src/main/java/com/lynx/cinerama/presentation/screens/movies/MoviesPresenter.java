package com.lynx.cinerama.presentation.screens.movies;

import android.util.Log;

import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.domain.MovieRepository;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MoviesPresenter implements MoviesContract.MoviesPresenter {

    private MoviesContract.MoviesView view;
    private MovieRepository movieRepository;
    private int movieID;
    private CompositeSubscription compositeSubscription;


    public MoviesPresenter(MoviesContract.MoviesView view, MovieRepository movieRepository, int movieID) {
        this.view = view;
        this.movieRepository = movieRepository;
        this.movieID = movieID;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.displayProgress(true);
        compositeSubscription.add(
                movieRepository.getMovieInfo(movieID)
                        .subscribe(responseMovieInfo -> {
                            view.displayProgress(false);
                            Log.d("myLogs", "Success");

                            view.setTitle(responseMovieInfo.title);
                            view.setBackdropImage(responseMovieInfo.backdrop_path);
                            view.setupMovieInfo(responseMovieInfo);
                            view.setupBottomBar();
                        }, t -> {
                            view.displayProgress(false);
                            view.displayError(t.getMessage());
                        })
        );
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
