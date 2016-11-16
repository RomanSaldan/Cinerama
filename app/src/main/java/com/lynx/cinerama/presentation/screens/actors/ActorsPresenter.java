package com.lynx.cinerama.presentation.screens.actors;

import com.lynx.cinerama.domain.ActorRepository;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 11/15/2016.
 */

public class ActorsPresenter implements ActorsContract.ActorsPresenter {

    private ActorsContract.ActorsView view;
    private ActorRepository actorRepository;
    private int actorID;
    private CompositeSubscription compositeSubscription;

    public ActorsPresenter(ActorsContract.ActorsView view, ActorRepository actorRepository, int actorID) {
        this.view = view;
        this.actorRepository = actorRepository;
        this.actorID = actorID;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
