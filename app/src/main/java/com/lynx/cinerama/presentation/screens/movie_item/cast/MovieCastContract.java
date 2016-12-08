package com.lynx.cinerama.presentation.screens.movie_item.cast;

import com.lynx.cinerama.data.model.movies.credits.MovieCredits;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.MovieCastDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public interface MovieCastContract {
    interface MovieCastView extends BaseView<MovieCastPresenter> {
        void displayCredits(ArrayList<MovieCastDH> movieCastDHs);
        void clickCreditPerson(int personID);

        void openPersonInfoScreen(int personID);
    }
    interface MovieCastPresenter extends BasePresenter {
        void setupCredits(MovieCredits movieCredits);

        void setupPersonInfoScreen(int personID);
    }
}
