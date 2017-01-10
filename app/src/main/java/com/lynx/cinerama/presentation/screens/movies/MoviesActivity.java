package com.lynx.cinerama.presentation.screens.movies;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.lynx.cinerama.R;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;
import com.lynx.cinerama.presentation.screens.NavigationActivity;
import com.lynx.cinerama.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 12/8/2016.
 */

@EActivity(R.layout.activity_movies2)
public class MoviesActivity extends NavigationActivity implements MoviesContract.MoviesView {

    private MoviesContract.MoviesPresenter presenter;

    @ViewById
    protected Toolbar tbMovies_AM;
//    @ViewById
//    protected Toolbar tbGenres_AM;

    @Bean
    protected MovieRepository movieRepository;

    @AfterInject
    protected void initPresenter() {
        new MoviesPresenter(this, movieRepository);
    }

    @AfterViews
    protected void initUI() {
        navigationView.setCheckedItem(R.id.menuItemMovies);
        setupToolbar();

        presenter.subscribe();
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(tbMovies_AM);
        getSupportActionBar().setTitle("TITLE");
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, fullView, tbMovies_AM,
                R.string.drawer_open, R.string.drawer_close);
        fullView.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setLayout(Constants.LayoutType layoutType) {

    }

    @Override
    public void setupMoves(ArrayList<String> movieDHs) {

    }

    @Override
    public void addGenre(String genre) {

    }

    @Override
    public void removeGenre(String genre) {

    }

    @Override
    public void startGenrePicker() {

    }

    @Override
    public void displaySearchResults(ArrayList<SearchResultDH> resultDHs) {

    }

    @Override
    public void startPersonScreen(int personID) {

    }

    @Override
    public void setPresenter(MoviesContract.MoviesPresenter presenter) {
        this.presenter = presenter;
    }
}
