package com.lynx.cinerama.presentation.screens.movies.info.review_details;

import com.lynx.cinerama.data.model.movies.reviews.MovieReviews;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/28/2016.
 */

public interface ReviewDetailsContract {
    interface ReviewDetailsView extends BaseView<ReviewDetailsPresenter> {
        void setupToolbar(String title);
        void displayReviews(ArrayList<ReviewDH> reviewDHs);
        void clickReviewLink(String url);
        void displayReviewLink(String url);
    }
    interface ReviewDetailsPresenter extends BasePresenter {
        void loadMoreReviews(int page);
        void setupMovieReviews(MovieReviews movieReviews);
        void openReviewLink(String url);
    }
}
