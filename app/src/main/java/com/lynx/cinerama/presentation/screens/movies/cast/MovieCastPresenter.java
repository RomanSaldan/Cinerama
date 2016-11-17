package com.lynx.cinerama.presentation.screens.movies.cast;

import com.lynx.cinerama.data.model.movies.credits.MovieCredits;
import com.lynx.cinerama.data.model.movies.credits.PersonCast;
import com.lynx.cinerama.data.model.movies.credits.PersonCrew;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.holders.data.MovieCastDH;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieCastPresenter implements MovieCastContract.MovieCastPresenter {

    private MovieCastContract.MovieCastView view;
    private int movieID;
    private MovieRepository movieRepository;
    private CompositeSubscription compositeSubscription;

    public MovieCastPresenter(MovieCastContract.MovieCastView view, int movieID, MovieRepository movieRepository) {
        this.view = view;
        this.movieID = movieID;
        this.movieRepository = movieRepository;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void setupCredits(MovieCredits movieCredits) {
        ArrayList<MovieCastDH> movieCastDHs = new ArrayList<>();

        if(movieCredits.cast != null && movieCredits.cast.size() > 0) {
            movieCastDHs.add(new MovieCastDH("CAST"));
            for(PersonCast personCast : movieCredits.cast)
                movieCastDHs.add(new MovieCastDH(personCast));
        }
        if(movieCredits.crew != null && movieCredits.crew.size() > 0) {
            movieCastDHs.add(new MovieCastDH("CREW"));
            for(PersonCrew personCrew : movieCredits.crew)
                movieCastDHs.add(new MovieCastDH(personCrew));
        }
        view.displayCredits(movieCastDHs);
    }

    @Override
    public void setupPersonInfoScreen(int personID) {
        view.openPersonInfoScreen(personID);
    }

    @Override
    public void subscribe() {
        compositeSubscription.add(
                movieRepository.getMovieCredits(movieID)
                    .subscribe(this::setupCredits)
        );

    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
