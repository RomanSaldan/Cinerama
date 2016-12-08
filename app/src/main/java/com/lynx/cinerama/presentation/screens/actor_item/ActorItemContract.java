package com.lynx.cinerama.presentation.screens.actor_item;

import com.lynx.cinerama.data.model.actors.ResponseActorInfo;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/15/2016.
 */

public interface ActorItemContract {
    interface ActorsView extends BaseView<ActorsPresenter> {
        void displayProgress(boolean isShown);
        void displayError(String msg);

        void setupToolbar();
        void displayActorName(String actorName);
        void displayActorImage(String path);
        void setupBottomBar();
        void setupActorInfo(ResponseActorInfo responseActorInfo);

        void refreshActorInfo(int actorID);
        void displaySearchResults(ArrayList<SearchResultDH> resultDHs);
        void startMovieScreen(int movieID);
    }
    interface ActorsPresenter extends BasePresenter {
        void search(String query);
        void selectSearchResult(SearchResultDH searchResultDH);
    }
}
