package com.lynx.cinerama.presentation.screens.movies.info;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.data.model.movies.credits.MovieCredits;
import com.lynx.cinerama.data.model.movies.info.MovieGenre;
import com.lynx.cinerama.data.model.movies.info.MovieProductionCountry;
import com.lynx.cinerama.data.model.movies.reviews.MovieReview;
import com.lynx.cinerama.data.model.movies.reviews.MovieReviews;
import com.lynx.cinerama.data.model.movies.similar.MovieSimilar;
import com.lynx.cinerama.data.model.movies.similar.ShortMovieInfo;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;
import com.lynx.cinerama.presentation.holders.data.SimilarDH;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieInfoPresenter implements MovieInfoContract.MovieInfoPresenter {

    private MovieInfoContract.MovieInfoView view;
    private int movieID;
    private MovieRepository movieRepository;
    private CompositeSubscription compositeSubscription;

    private Bitmap poster;

    public MovieInfoPresenter(MovieInfoContract.MovieInfoView view, int movieID, MovieRepository movieRepository) {
        this.view = view;
        this.movieID = movieID;
        this.movieRepository = movieRepository;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void displayMovieInfo(ResponseMovieInfo responseMovieInfo) {
        initBasicMovieInfo(responseMovieInfo);
        initMovieAdditionalInfo(responseMovieInfo);
        initMovieCircleCast(responseMovieInfo.credits);
        initSimilars(responseMovieInfo.similar);
        initReviews(responseMovieInfo.reviews);
    }

    @Override
    public void startImdbScreen() {
        compositeSubscription.add(
                movieRepository.getMovieInfo(movieID)
                .subscribe(responseMovieInfo -> {
                    view.startImmdbIntent(responseMovieInfo.imdb_id);
                })
        );
    }

    @Override
    public void startWebScreen() {
        compositeSubscription.add(
                movieRepository.getMovieInfo(movieID)
                        .subscribe(responseMovieInfo -> {
                            view.startWebIntent(responseMovieInfo.homepage);
                        })
        );
    }

    @Override
    public void startSimilarMovieScreen(int movieId) {
        view.refreshMovieInfo(movieId);
    }

    @Override
    public void startMoreSimilarMovies() {
        compositeSubscription.add(
                movieRepository.getMovieInfo(movieID)
                        .subscribe(responseMovieInfo -> {
                            view.startMoreSimilars(responseMovieInfo.id);
                        })
        );
    }

    @Override
    public void startMoreCast() {
        view.startMoreCast();
    }

    @Override
    public void startCastItem(int personId) {
        Log.d("myLogs", "Clicked cast item || id = " + personId);
    }

    @Override
    public void openFullscreenPoster() {
        if(poster != null)
        view.startFullscreenPoster(poster);
    }

    @Override
    public void startReviewsMore() {
        compositeSubscription.add(
                movieRepository.getMovieInfo(movieID)
                        .subscribe(responseMovieInfo -> {
                            view.startMoreReviewActivity(responseMovieInfo.id);
                        })
        );
    }

    @Override
    public Bitmap getPosterBitmap() {
        return poster;
    }

    @Override
    public void setPosterBitmap(Bitmap posterBitmap) {
        poster = posterBitmap;
    }

    @Override
    public void subscribe() {
        compositeSubscription.add(
                movieRepository.getMovieInfo(movieID)
                .subscribe(this::displayMovieInfo)
        );
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private void initBasicMovieInfo(ResponseMovieInfo movieInfo) {
        if (!TextUtils.isEmpty(movieInfo.poster_path))
            view.displayPosterImage(movieInfo.poster_path);
        else
            view.setPosterClickable(false);
        if (!TextUtils.isEmpty(movieInfo.title))
            view.displayTitle(movieInfo.title);
        if (!TextUtils.isEmpty(movieInfo.tagline))
            view.displayTagline(movieInfo.tagline);
        if (movieInfo.genres != null && movieInfo.genres.size() > 0)
            view.displayGenres(buildMovieGenresString(movieInfo.genres));
        if (movieInfo.production_countries != null && movieInfo.production_countries.size() > 0)
            view.displayMovieCountries(buildMovieCountriesString(movieInfo.production_countries));
        if (movieInfo.production_companies != null && movieInfo.production_companies.size() > 0)
            view.displayMovieCompanies(movieInfo.production_companies.get(0).name);
    }

    private void initMovieAdditionalInfo(ResponseMovieInfo movieInfo) {
        if (movieInfo.vote_count > 0 && movieInfo.vote_average > 0)
            view.displayRating(getPrettyRating(movieInfo.vote_average, movieInfo.vote_count));
        if (movieInfo.runtime > 0)
            view.displayDuration(getPrettyDuration(movieInfo.runtime));
        if (!TextUtils.isEmpty(movieInfo.status))
            view.displayReleaseStatus(movieInfo.status);
        if (!TextUtils.isEmpty(movieInfo.release_date))
            view.displayReleaseDate(getPrettyDate(movieInfo.release_date));
        if (movieInfo.budget > 0)
            view.displayBudget(getPrettyAmount(movieInfo.budget));
        if (movieInfo.revenue > 0)
            view.displayRevenue(getPrettyAmount(movieInfo.revenue));
        if (!TextUtils.isEmpty(movieInfo.overview))
            view.displayMovieOverview(movieInfo.overview);
        view.enableImdbButton(!TextUtils.isEmpty(movieInfo.imdb_id));
        view.enableWebButton(!TextUtils.isEmpty(movieInfo.homepage));
    }

    private void initSimilars(MovieSimilar movieSimilar) {
        if (movieSimilar.results != null && movieSimilar.results.size() > 0) {
            ArrayList<SimilarDH> listDHs = new ArrayList<>();
            for (ShortMovieInfo smi : movieSimilar.results)
                listDHs.add(new SimilarDH(smi));
            view.setupMovieSimilar(listDHs);
        } else
            view.setSimilarVisibility(false);
    }

    private void initReviews(MovieReviews movieReviews) {
        if (movieReviews != null && movieReviews.total_results > 0) {
            ArrayList<ReviewDH> listDHs = new ArrayList<>();
            for (MovieReview mr : movieReviews.results)
                listDHs.add(new ReviewDH(mr));

            view.setupMovieReviews(listDHs);
        } else
            view.setReviewVisibility(false);
    }

    private void initMovieCircleCast(MovieCredits data) {
        if (data != null) {
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
        if (!genres.isEmpty()) {
            for (MovieGenre mg : genres) result += mg.name + ", ";
            return result.substring(0, result.length() - 2).toLowerCase();
        }
        return null;
    }

    @Nullable
    private String buildMovieCountriesString(ArrayList<MovieProductionCountry> countries) {
        String result = "";
        if (!countries.isEmpty()) {
            for (MovieProductionCountry mpc : countries) result += mpc.name + ", ";
            return result.substring(0, result.length() - 2);
        }
        return null;
    }
}
