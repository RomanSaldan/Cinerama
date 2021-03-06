package com.lynx.cinerama.presentation.screens.movies;

import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;

/**
 * Created by Lynx on 10/26/2016.
 */

public interface MoviesContract {
    interface MoviesView extends BaseView<MoviesPresenter> {
        void displayProgress(boolean isShown);
        void displayError(String msg);

        void setupToolbar(String title);
        void setupBottomBar();
        void setBackdropImage(String backdropPath);
        void setupMovieInfo(ResponseMovieInfo responseMovieInfo);
    }
    interface MoviesPresenter extends BasePresenter {

    }
}
