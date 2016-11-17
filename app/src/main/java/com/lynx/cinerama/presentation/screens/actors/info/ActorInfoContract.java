package com.lynx.cinerama.presentation.screens.actors.info;

import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;

/**
 * Created by Lynx on 11/16/2016.
 */

public interface ActorInfoContract {
    interface ActorInfoView extends BaseView<ActorInfoPresenter> {
        void displayActorName(String name);
        void displayKnownAs(String knownAs);
        void displayActorImage(String path);
        void enableWebButton(boolean isEnabled);
        void enableImdbButton(boolean isEnabled);
        void displayBirthplace(String birthplace);
        void displayBirthdate(String birthdate);
        void displayBiography(String biography);

        void clickWeb(Void v);
        void clickIMDB(Void v);
        void clickActorImage(Void v);

        void displayActorWebpage(String url);
        void displayActorImdbPage(String url);
    }
    interface ActorInfoPresenter extends BasePresenter {
        void startActorWebpage();
        void startActorImdbPage();
    }
}
