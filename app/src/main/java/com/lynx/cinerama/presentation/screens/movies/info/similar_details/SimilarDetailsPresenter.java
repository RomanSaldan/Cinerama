package com.lynx.cinerama.presentation.screens.movies.info.similar_details;

import android.util.Log;

import com.lynx.cinerama.data.model.movies.similar.MovieSimilar;
import com.lynx.cinerama.data.model.movies.similar.ShortMovieInfo;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.holders.data.SimilarDH;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 10/28/2016.
 */

public class SimilarDetailsPresenter implements SimilarDetailsContract.SimilarDetailsPresenter {

    private SimilarDetailsContract.SimilarDetailsView view;
    private int movieID;
    private MovieRepository movieRepository;
    private CompositeSubscription compositeSubscription;

    private int currentPage = 1;

    public SimilarDetailsPresenter(SimilarDetailsContract.SimilarDetailsView view, int movieID, MovieRepository movieRepository) {
        this.view = view;
        this.movieID = movieID;
        this.movieRepository = movieRepository;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void loadMoreSimilars(int page) {
        compositeSubscription.add(
                movieRepository.getMovieSimilar(movieID, page)
                .subscribe(movieSimilar -> {
                    currentPage = movieSimilar.page;
                    view.displayMoreSimilars(prepareSimilarDHs(movieSimilar));
                }, t -> Log.d("myLogs", t.getMessage()))
        );
    }

    @Override
    public void startMovieInfoScreen(int movieID) {
        view.displayMovieInfoScreen(movieID);
    }

    @Override
    public void subscribe() {
        compositeSubscription.add(
                movieRepository.getMovieInfo(movieID)
                    .subscribe(responseMovieInfo -> view.setupToolbar(responseMovieInfo.title))
        );
        loadMoreSimilars(currentPage);
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private ArrayList<SimilarDH> prepareSimilarDHs(MovieSimilar movieSimilar) {
        ArrayList<SimilarDH> result = new ArrayList<>();
        for(ShortMovieInfo smi : movieSimilar.results)
        result.add(new SimilarDH(smi));
        return result;
    }
}
