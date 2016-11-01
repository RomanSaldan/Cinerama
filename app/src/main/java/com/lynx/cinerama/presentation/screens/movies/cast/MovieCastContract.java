package com.lynx.cinerama.presentation.screens.movies.cast;

import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.CreditsDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public interface MovieCastContract {
    interface MovieCastView extends BaseView<MovieCastPresenter> {
        void displayCredits(ArrayList<CreditsDH> creditsDHs);
        void clickCreditPerson(int personID);

        void openPersonInfoScreen(int personID);
    }
    interface MovieCastPresenter extends BasePresenter {
        void setupCredits();

        void setupPersonInfoScreen(int personID);
    }
}
