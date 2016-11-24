package com.lynx.cinerama.presentation.screens.actors.scenes;

import android.util.Log;
import android.view.View;

import com.lynx.cinerama.data.model.actors.profile_tagged_image.ActorTaggedImages;
import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.domain.ActorRepository;
import com.lynx.cinerama.presentation.holders.data.SceneDH;

import java.util.ArrayList;

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
    private int totalImages;
    private ArrayList<ImageModel> cachedImages;

    public ActorScenesPresenter(ActorScenesContract.ActorScenesView view, ActorRepository actorRepository, int actorID) {
        this.view = view;
        this.actorRepository = actorRepository;
        this.actorID = actorID;
        compositeSubscription = new CompositeSubscription();
        cachedImages = new ArrayList<>();

        view.setPresenter(this);
    }

    @Override
    public void loadMoreActorScenes(int page) {
        compositeSubscription.add(
                actorRepository.getActorScenes(actorID, currentPage)
                        .subscribe(actorTaggedImages -> {
                            cachedImages.addAll(actorTaggedImages.results);
                            currentPage = ++ actorTaggedImages.page;
                            totalImages = actorTaggedImages.total_results;
                            view.displayActorScenes(getActorSceneDHs(actorTaggedImages));
                        }, t -> Log.d("err", t.getMessage()))
        );
    }

    @Override
    public void startSceneGallery(View v, int pos) {
        view.displaySceneGallery(v, pos, actorID, cachedImages, currentPage, totalImages);
    }

    @Override
    public void subscribe() {
        loadMoreActorScenes(currentPage);
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private ArrayList<SceneDH> getActorSceneDHs(ActorTaggedImages actorTaggedImages) {
        ArrayList<SceneDH> result = new ArrayList<>();
        if(actorTaggedImages != null && actorTaggedImages.results != null && actorTaggedImages.results.size() > 0) {
            for (ImageModel imageModel : actorTaggedImages.results)
                result.add(new SceneDH(imageModel));
        }
        return result;
    }
}
