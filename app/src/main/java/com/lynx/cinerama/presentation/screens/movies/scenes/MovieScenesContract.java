package com.lynx.cinerama.presentation.screens.movies.scenes;

import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.SceneDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public interface MovieScenesContract {
    interface MovieScencesView extends BaseView<MovieScenesPresenter> {
        void displayScenes(ArrayList<SceneDH> sceneDHs);
        void clickScene(int pos);

        void displaySceneGallary(int pos);
    }
    interface MovieScenesPresenter extends BasePresenter {
        void setupMovieScenes();
        void startSceneGallery(int pos);
    }
}
