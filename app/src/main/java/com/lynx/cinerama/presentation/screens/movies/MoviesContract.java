package com.lynx.cinerama.presentation.screens.movies;

import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;
import com.lynx.cinerama.presentation.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/3/2017.
 */

public interface MoviesContract {
    interface MoviesView extends BaseView<MoviesPresenter> {
        void displayProgress(boolean isShown);
        void displayError(String msg);

        void setupToolbar();
        void setTitle(String title);
        void setLayout(Constants.LayoutType layoutType);

        void setupMoves(ArrayList<String> movieDHs);
        void addGenre(String genre);
        void removeGenre(String genre);
        void startGenrePicker();

        void displaySearchResults(ArrayList<SearchResultDH> resultDHs);
        void startPersonScreen(int personID);
    }
    interface MoviesPresenter extends BasePresenter {
        void search(String query);
        void selectSearchResult(SearchResultDH searchResultDH);

        void chageGenre();
        void getMovies(Constants.MovieFilter filter);
    }
}
