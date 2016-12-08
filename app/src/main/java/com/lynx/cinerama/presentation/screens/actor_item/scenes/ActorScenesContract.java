package com.lynx.cinerama.presentation.screens.actor_item.scenes;

import android.view.View;

import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.SceneDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/16/2016.
 */

public interface ActorScenesContract {
    interface ActorScenesView extends BaseView<ActorScenesPresenter> {
        void displayActorScenes(ArrayList<SceneDH> sceneDHs);
        void clickActorScene(View v, int pos);

        void displaySceneGallery(View v, int pos, int actorID, ArrayList<ImageModel> cachedImages, int page, int totalImages);
    }
    interface ActorScenesPresenter extends BasePresenter {
        void loadMoreActorScenes(int page);
        void startSceneGallery(View v, int pos);
    }
}