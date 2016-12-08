package com.lynx.cinerama.presentation.screens.movie_item;

import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public interface MovieItemContract {
    interface MoviesView extends BaseView<MoviesPresenter> {
        void displayProgress(boolean isShown);
        void displayError(String msg);

        void setupToolbar();
        void setTitle(String title);
        void setupBottomBar();
        void setBackdropImage(String backdropPath);
        void setupMovieInfo(ResponseMovieInfo responseMovieInfo);

        void refreshMovieInfo(int movieID);
        void openCast();

        void displaySearchResults(ArrayList<SearchResultDH> resultDHs);
        void startPersonScreen(int personID);
    }
    interface MoviesPresenter extends BasePresenter {
        void search(String query);
        void selectSearchResult(SearchResultDH searchResultDH);
    }
}
