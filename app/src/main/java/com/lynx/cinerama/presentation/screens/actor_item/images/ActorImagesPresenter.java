package com.lynx.cinerama.presentation.screens.actor_item.images;

import android.view.View;

import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.domain.ActorRepository;
import com.lynx.cinerama.presentation.holders.data.PosterDH;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 11/16/2016.
 */

public class ActorImagesPresenter implements ActorImagesContract.ActorImagesPresenter {

    private ActorImagesContract.ActorImagesView view;
    private ActorRepository actorRepository;
    private int actorID;
    private CompositeSubscription compositeSubscription;

    public ActorImagesPresenter(ActorImagesContract.ActorImagesView view, ActorRepository actorRepository, int actorID) {
        this.view = view;
        this.actorRepository = actorRepository;
        this.actorID = actorID;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void setupImages(List<ImageModel> images) {
        ArrayList<PosterDH> result = new ArrayList<>();
        if(images != null && images.size() > 0) {
            for(ImageModel im : images)
                result.add(new PosterDH(im));
        }
        view.displayActorImages(result);
    }

    @Override
    public void startImageGallery(View v, int pos) {
        view.startImageGalleryScreen(v, pos, actorID);
    }

    @Override
    public void subscribe() {
        compositeSubscription.add(
                actorRepository.getActorImages(actorID)
                        .subscribe(this::setupImages)
        );
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
