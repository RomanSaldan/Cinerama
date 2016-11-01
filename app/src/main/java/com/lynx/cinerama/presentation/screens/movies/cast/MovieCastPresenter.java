package com.lynx.cinerama.presentation.screens.movies.cast;

import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.data.model.movies.credits.PersonCast;
import com.lynx.cinerama.data.model.movies.credits.PersonCrew;
import com.lynx.cinerama.presentation.holders.data.CreditsDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieCastPresenter implements MovieCastContract.MovieCastPresenter {

    private MovieCastContract.MovieCastView view;
    private ResponseMovieInfo responseMovieInfo;

    public MovieCastPresenter(MovieCastContract.MovieCastView view, ResponseMovieInfo responseMovieInfo) {
        this.view = view;
        this.responseMovieInfo = responseMovieInfo;

        view.setPresenter(this);
    }

    @Override
    public void setupCredits() {
        ArrayList<CreditsDH> creditsDHs = new ArrayList<>();
        if(responseMovieInfo.credits.cast != null && responseMovieInfo.credits.cast.size() > 0) {
            creditsDHs.add(new CreditsDH("CAST"));
            for(PersonCast personCast : responseMovieInfo.credits.cast)
                creditsDHs.add(new CreditsDH(personCast));
        }
        if(responseMovieInfo.credits.crew != null && responseMovieInfo.credits.crew.size() > 0) {
            creditsDHs.add(new CreditsDH("CREW"));
            for(PersonCrew personCrew : responseMovieInfo.credits.crew)
                creditsDHs.add(new CreditsDH(personCrew));
        }
        view.displayCredits(creditsDHs);
    }

    @Override
    public void setupPersonInfoScreen(int personID) {
        view.openPersonInfoScreen(personID);
    }

    @Override
    public void subscribe() {
        setupCredits();
    }

    @Override
    public void unsubscribe() {

    }
}
