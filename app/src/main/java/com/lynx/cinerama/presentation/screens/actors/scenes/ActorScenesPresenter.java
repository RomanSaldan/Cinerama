package com.lynx.cinerama.presentation.screens.actors.scenes;

import android.view.View;

import com.lynx.cinerama.domain.ActorRepository;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 11/16/2016.
 */

public class ActorScenesPresenter implements ActorScenesContract.ActorScenesPresenter {

    private ActorScenesContract.ActorScenesView view;
    private ActorRepository actorRepository;
    private int actorID;
    private CompositeSubscription compositeSubscription;

    private int currentPage = 1;

    public ActorScenesPresenter(ActorScenesContract.ActorScenesView view, ActorRepository actorRepository, int actorID) {
        this.view = view;
        this.actorRepository = actorRepository;
        this.actorID = actorID;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void loadMoreActorScenes(int page) {

    }

    @Override
    public void startSceneGallery(View v, int pos) {

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
