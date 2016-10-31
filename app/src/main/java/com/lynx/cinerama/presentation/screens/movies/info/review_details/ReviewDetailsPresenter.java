package com.lynx.cinerama.presentation.screens.movies.info.review_details;

import android.text.TextUtils;

import com.lynx.cinerama.data.model.movies.reviews.MovieReview;
import com.lynx.cinerama.data.model.movies.reviews.MovieReviews;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/28/2016.
 */

public class ReviewDetailsPresenter implements ReviewDetailsContract.ReviewDetailsPresenter {

    private ReviewDetailsContract.ReviewDetailsView view;
    private String movieTitle;
    private MovieReviews movieReviews;

    public ReviewDetailsPresenter(ReviewDetailsContract.ReviewDetailsView view, String movieTitle, MovieReviews movieReviews) {
        this.view = view;
        this.movieTitle = movieTitle;
        this.movieReviews = movieReviews;

        view.setPresenter(this);
    }

    @Override
    public void openReviewLink(String url) {
        if (!TextUtils.isEmpty(url))
            view.displayReviewLink(url);
    }

    @Override
    public void subscribe() {
        view.setupToolbar(movieTitle);

        ArrayList<ReviewDH> reviewDHs = new ArrayList<>();
        for(MovieReview mr : movieReviews.results)
            reviewDHs.add(new ReviewDH(mr));
        view.displayReviews(reviewDHs);
    }

    @Override
    public void unsubscribe() {

    }
}
