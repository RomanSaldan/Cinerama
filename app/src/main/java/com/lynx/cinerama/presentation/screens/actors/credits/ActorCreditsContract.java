package com.lynx.cinerama.presentation.screens.actors.credits;

import android.view.View;

import com.lynx.cinerama.data.model.actors.credits.ActorCredits;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.ActorCreditsDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/16/2016.
 */

public interface ActorCreditsContract {
    interface ActorCreditsView extends BaseView<ActorCreditsPresenter> {
        void displayActorCredits(ArrayList<ActorCreditsDH> creditsDHs);

        void clickActorCredit(int movieID);
        void openMovieScreen(int movieID);
    }
    interface ActorCreditsPresenter extends BasePresenter {
        void setupActorCredits(ActorCredits actorCredits);
        void setupMovieInfoScreen(int movieID);
    }
}
