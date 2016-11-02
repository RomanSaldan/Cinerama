package com.lynx.cinerama.presentation.screens.movies.info;

import android.content.Context;
import android.graphics.Bitmap;

import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.data.model.movies.credits.MovieCredits;
import com.lynx.cinerama.data.model.movies.reviews.MovieReviews;
import com.lynx.cinerama.data.model.movies.similar.MovieSimilar;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;
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
        void setupMovieReviews(ArrayList<ReviewDH> reviewDHs);

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

        void startMoreReviewActivity(int movieID);
        void startImmdbIntent(String imdbID);
        void startWebIntent(String url);
        void startMoreCast();
        void startMoreSimilars(int movieID);
        void startFullscreenPoster(Bitmap posterBitmap);

        void refreshMovieInfo(int movieID);
    }
    interface MovieInfoPresenter extends BasePresenter {
        void displayMovieInfo(ResponseMovieInfo responseMovieInfo);
        void startImdbScreen();
        void startWebScreen();
        void startSimilarMovieScreen(int movieId);
        void startMoreSimilarMovies();
        void startMoreCast();
        void startCastItem(int personId);
        void openFullscreenPoster();
        void startReviewsMore();

        Bitmap getPosterBitmap();
        void setPosterBitmap(Bitmap posterBitmap);
    }
}
