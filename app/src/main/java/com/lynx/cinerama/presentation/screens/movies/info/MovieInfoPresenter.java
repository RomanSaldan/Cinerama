package com.lynx.cinerama.presentation.screens.movies.info;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.data.model.movies.credits.MovieCredits;
import com.lynx.cinerama.data.model.movies.info.MovieGenre;
import com.lynx.cinerama.data.model.movies.info.MovieProductionCountry;
import com.lynx.cinerama.data.model.movies.reviews.MovieReviews;
import com.lynx.cinerama.data.model.movies.similar.MovieSimilar;
import com.lynx.cinerama.data.model.movies.similar.ShortMovieInfo;
import com.lynx.cinerama.presentation.holders.data.SimilarDH;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieInfoPresenter implements MovieInfoContract.MovieInfoPresenter {

    private MovieInfoContract.MovieInfoView view;
    private ResponseMovieInfo movieInfo;

    public MovieInfoPresenter(MovieInfoContract.MovieInfoView view, ResponseMovieInfo movieInfo) {
        this.view = view;
        this.movieInfo = movieInfo;

        view.setPresenter(this);
    }

    @Override
    public void displayMovieInfo() {
        initBasicMovieInfo(movieInfo);
        initMovieAdditionalInfo(movieInfo);
        initMovieCircleCast(movieInfo.credits);
        initSimilars(movieInfo.similar);
        initReviews(movieInfo.reviews);
    }

    @Override
    public void startImdbScreen(Context context) {

    }

    @Override
    public void startWebScreen(Context context) {

    }

    @Override
    public void startSimilarMovieScreen(int movieId) {
        Log.d("myLogs", "Start similar movie info. ID = " + movieId);
    }

    @Override
    public void startMoreSimilarMovies() {

    }

    @Override
    public void startMoreReviewsScreen() {

    }

    @Override
    public void startMoreCast() {

    }

    @Override
    public void startCastItem(int personId) {

    }

    @Override
    public void openFullscreenPoster(Context context) {

    }

    @Override
    public void startReviewsMore() {

    }

    @Override
    public void subscribe() {
        displayMovieInfo();
    }

    @Override
    public void unsubscribe() {

    }

    private void initBasicMovieInfo(ResponseMovieInfo movieInfo) {
        if(!TextUtils.isEmpty(movieInfo.poster_path))
            view.displayPosterImage(movieInfo.poster_path);
        else
            view.setPosterClickable(false);
        if(!TextUtils.isEmpty(movieInfo.title))
            view.displayTitle(movieInfo.title);
        if(!TextUtils.isEmpty(movieInfo.tagline))
            view.displayTagline(movieInfo.tagline);
        if(movieInfo.genres != null && movieInfo.genres.size() > 0)
            view.displayGenres(buildMovieGenresString(movieInfo.genres));
        if(movieInfo.production_countries != null && movieInfo.production_countries.size() > 0)
            view.displayMovieCountries(buildMovieCountriesString(movieInfo.production_countries));
        if(movieInfo.production_companies != null && movieInfo.production_companies.size() > 0)
            view.displayMovieCompanies(movieInfo.production_companies.get(0).name);
    }

    private void initMovieAdditionalInfo(ResponseMovieInfo movieInfo) {
        if(movieInfo.vote_count > 0 && movieInfo.vote_average > 0)
            view.displayRating(getPrettyRating(movieInfo.vote_average, movieInfo.vote_count));
        if(movieInfo.runtime > 0)
            view.displayDuration(getPrettyDuration(movieInfo.runtime));
        if(!TextUtils.isEmpty(movieInfo.status))
            view.displayReleaseStatus(movieInfo.status);
        if(!TextUtils.isEmpty(movieInfo.release_date))
            view.displayReleaseDate(getPrettyDate(movieInfo.release_date));
        if(movieInfo.budget > 0)
            view.displayBudget(getPrettyAmount(movieInfo.budget));
        if(movieInfo.revenue > 0)
            view.displayRevenue(getPrettyAmount(movieInfo.revenue));
        if(!TextUtils.isEmpty(movieInfo.overview))
            view.displayMovieOverview(movieInfo.overview);
        view.enableImdbButton(!TextUtils.isEmpty(movieInfo.imdb_id));
        view.enableWebButton(!TextUtils.isEmpty(movieInfo.homepage));
    }

    private void initSimilars(MovieSimilar movieSimilar) {
        if(movieSimilar.results != null && movieSimilar.results.size() > 0) {
            ArrayList<SimilarDH> listDHs = new ArrayList<>();
            for(ShortMovieInfo smi : movieSimilar.results)
                listDHs.add(new SimilarDH(smi));
            view.setupMovieSimilar(listDHs);
        }
        else
            view.setSimilarVisibility(false);
    }

    private void initReviews(MovieReviews movieReviews) {
        if(movieReviews != null && movieReviews.total_results > 0) {
            view.setupMovieReviews(movieReviews);
        } else
            view.setReviewVisibility(false);
    }

    private void initMovieCircleCast(MovieCredits data) {
        if(data != null) {
            if (data.crew != null && data.crew.size() > 0
                    || data.cast != null && data.cast.size() > 0)
                view.setupCircleCastView(data);
            else
                view.setCastVisibility(false);
        }
    }

    private String getPrettyAmount(long amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        nf.setMaximumFractionDigits(0);
        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);
        return nf.format(amount);
    }

    private String getPrettyRating(double voteAverge, int voteCount) {
        return voteAverge + " (" + voteCount + " votes)";
    }

    private String getPrettyDuration(int runtime) {
        int h = runtime / 60;
        int m = runtime % 60;
        return String.format("%d min (%d:%d)", runtime, h, m);
    }

    private String getPrettyDate(String ugleDate) {
        String[] arr = ugleDate.split("-");
        return String.format("%s.%s.%s", arr[1], arr[2], arr[0]);
    }

    @Nullable
    private String buildMovieGenresString(ArrayList<MovieGenre> genres) {
        String result = "";
        if(!genres.isEmpty()) {
            for (MovieGenre mg : genres) result += mg.name + ", ";
            return result.substring(0, result.length() - 2).toLowerCase();
        }
        return null;
    }

    @Nullable
    private String buildMovieCountriesString(ArrayList<MovieProductionCountry> countries) {
        String result = "";
        if(!countries.isEmpty()) {
            for (MovieProductionCountry mpc : countries) result += mpc.name + ", ";
            return result.substring(0, result.length() - 2);
        }
        return null;
    }
}
