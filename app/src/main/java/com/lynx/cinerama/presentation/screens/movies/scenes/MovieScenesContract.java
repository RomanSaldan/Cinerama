package com.lynx.cinerama.presentation.screens.movies.scenes;

import android.view.View;

import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.SceneDH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 10/26/2016.
 */

public interface MovieScenesContract {
    interface MovieScencesView extends BaseView<MovieScenesPresenter> {
        void displayScenes(ArrayList<SceneDH> sceneDHs);
        void clickScene(View v, int pos);

        void displaySceneGallery(View v, int pos, int movieID);
    }
    interface MovieScenesPresenter extends BasePresenter {
        void setupMovieScenes(List<ImageModel> scenes);
        void startSceneGallery(View v, int pos);
    }
}
