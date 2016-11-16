package com.lynx.cinerama.presentation.screens.actors;

import com.lynx.cinerama.data.model.actors.ResponseActorInfo;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;

/**
 * Created by Lynx on 11/15/2016.
 */

public interface ActorsContract {
    interface ActorsView extends BaseView<ActorsPresenter> {
        void displayProgress(boolean isShown);
        void displayError(String msg);

        void setupToolbar();
        void displayActorName(String actorName);
        void displayActorImage(String path);
        void setupBottomBar();
        void setupActorInfo(ResponseActorInfo responseActorInfo);

        void refreshActorInfo(int actorID);
    }
    interface ActorsPresenter extends BasePresenter {

    }
}
