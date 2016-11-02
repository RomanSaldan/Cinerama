package com.lynx.cinerama.presentation.screens.movies.info.review_details;

import android.text.TextUtils;
import android.util.Log;

import com.lynx.cinerama.data.model.movies.reviews.MovieReview;
import com.lynx.cinerama.data.model.movies.reviews.MovieReviews;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 10/28/2016.
 */

public class ReviewDetailsPresenter implements ReviewDetailsContract.ReviewDetailsPresenter {

    private ReviewDetailsContract.ReviewDetailsView view;
    private int movieID;
    private MovieRepository movieRepository;
    private CompositeSubscription compositeSubscription;

    private int currentPage = 1;

    public ReviewDetailsPresenter(ReviewDetailsContract.ReviewDetailsView view, int movieID, MovieRepository movieRepository) {
        this.view = view;
        this.movieID = movieID;
        this.movieRepository = movieRepository;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void loadMoreReviews(int page) {
        compositeSubscription.add(
                movieRepository.getMovieReviews(movieID, page)
                    .subscribe(movieReviews -> {
                        currentPage = page;
                        setupMovieReviews(movieReviews);
                    }, t -> Log.d("myLogs", t.getMessage()))
        );
    }

    @Override
    public void setupMovieReviews(MovieReviews movieReviews) {
        ArrayList<ReviewDH> reviewDHs = new ArrayList<>();
        for(MovieReview mr : movieReviews.results)
            reviewDHs.add(new ReviewDH(mr));
        view.displayReviews(reviewDHs);
    }

    @Override
    public void openReviewLink(String url) {
        if (!TextUtils.isEmpty(url))
            view.displayReviewLink(url);
    }

    @Override
    public void subscribe() {
        compositeSubscription.add(
                movieRepository.getMovieInfo(movieID)
                .subscribe(responseMovieInfo -> view.setupToolbar(responseMovieInfo.title))
        );
        loadMoreReviews(currentPage);
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
