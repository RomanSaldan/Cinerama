package com.lynx.cinerama.presentation.screens.movies;

import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;
import com.lynx.cinerama.presentation.utils.Constants;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/3/2017.
 */

public class MoviesPresenter implements MoviesContract.MoviesPresenter {

    private MoviesContract.MoviesView view;
    private MovieRepository movieRepository;
    private CompositeSubscription compositeSubscription;

    public MoviesPresenter(MoviesContract.MoviesView view, MovieRepository movieRepository) {
        this.view = view;
        this.movieRepository = movieRepository;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void search(String query) {

    }

    @Override
    public void selectSearchResult(SearchResultDH searchResultDH) {

    }

    @Override
    public void chageGenre() {

    }

    @Override
    public void getMovies(Constants.MovieFilter filter) {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
