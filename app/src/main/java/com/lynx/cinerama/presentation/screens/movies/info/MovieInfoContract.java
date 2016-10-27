package com.lynx.cinerama.presentation.screens.movies.info;

import android.content.Context;

import com.lynx.cinerama.data.model.movies.credits.MovieCredits;
import com.lynx.cinerama.data.model.movies.reviews.MovieReviews;
import com.lynx.cinerama.data.model.movies.similar.MovieSimilar;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.SimilarDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public interface MovieInfoContract {
    interface MovieInfoView extends BaseView<MovieInfoPresenter> {
        void displayPosterImage(String url);
        void displayTitle(String title);
        void displayTagline(String tagline);
        void displayGenres(String genres);
        void displayMovieCompanies(String companies);
        void displayMovieCountries(String countries);
        void enableImdbButton(boolean isEnabled);
        void enableWebButton(boolean isEnabled);
        void displayRating(String rating);
        void displayBudget(String budget);
        void displayRevenue(String revenue);
        void displayReleaseStatus(String status);
        void displayReleaseDate(String date);
        void displayDuration(String duration);
        void setupCircleCastView(MovieCredits data);
        void displayMovieOverview(String overview);
        void setupMovieSimilar(ArrayList<SimilarDH> similarDHs);
        void setupMovieReviews(MovieReviews movieReviews);

        void setCastVisibility(boolean isVisible);
        void setSimilarVisibility(boolean isVisible);
        void setReviewVisibility(boolean isVisible);

        void setPosterClickable(boolean isClickable);

        void clickPoster();
        void clickCastItem(int personId);
        void clickCastMore();
        void clickImdbButton();
        void clickWebButton();
        void clickSimilarItem(int similarMovieId);
        void clickSimilarMore();
        void clickReviewsMore();
    }
    interface MovieInfoPresenter extends BasePresenter {
        void displayMovieInfo();
        void startImdbScreen(Context context);
        void startWebScreen(Context context);
        void startSimilarMovieScreen(int movieId);
        void startMoreSimilarMovies();
        void startMoreReviewsScreen();
        void startMoreCast();
        void startCastItem(int personId);
        void openFullscreenPoster(Context context);
        void startReviewsMore();
    }
}
