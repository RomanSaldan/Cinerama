package com.lynx.cinerama.presentation.screens.actors.credits;

import android.view.View;

import com.lynx.cinerama.data.model.actors.credits.ActorCreditCast;
import com.lynx.cinerama.data.model.actors.credits.ActorCreditCrew;
import com.lynx.cinerama.data.model.actors.credits.ActorCredits;
import com.lynx.cinerama.domain.ActorRepository;
import com.lynx.cinerama.presentation.holders.data.ActorCreditsDH;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 11/16/2016.
 */

public class ActorCreditsPresenter implements ActorCreditsContract.ActorCreditsPresenter {

    private ActorCreditsContract.ActorCreditsView view;
    private ActorRepository actorRepository;
    private int actorID;
    private CompositeSubscription compositeSubscription;

    public ActorCreditsPresenter(ActorCreditsContract.ActorCreditsView view, ActorRepository actorRepository, int actorID) {
        this.view = view;
        this.actorRepository = actorRepository;
        this.actorID = actorID;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void setupActorCredits(ActorCredits actorCredits) {
        if(actorCredits != null) {
            ArrayList<ActorCreditsDH> creditsDHs = new ArrayList<>();
            if(actorCredits.cast.size() > 0) {
                creditsDHs.add(new ActorCreditsDH("CAST", null, null));
                for(ActorCreditCast actorCreditCast : actorCredits.cast)
                    creditsDHs.add(new ActorCreditsDH(null, actorCreditCast, null));
            }
            if(actorCredits.crew.size() > 0) {
                creditsDHs.add(new ActorCreditsDH("CREW", null, null));
                for(ActorCreditCrew actorCreditCrew : actorCredits.crew)
                    creditsDHs.add(new ActorCreditsDH(null, null, actorCreditCrew));
            }
            view.displayActorCredits(creditsDHs);
        }
    }

    @Override
    public void setupMovieInfoScreen(int movieID) {
        view.openMovieScreen(movieID);
    }

    @Override
    public void subscribe() {
        compositeSubscription.add(
                actorRepository.getActorCredits(actorID)
                        .subscribe(this::setupActorCredits)
        );
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
