package com.lynx.cinerama.presentation.screens.movies;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lynx.cinerama.R;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.domain.SearchRepository;
import com.lynx.cinerama.presentation.adapters.MultiSearchAdapter;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;
import com.lynx.cinerama.presentation.screens.NavigationActivity;
import com.lynx.cinerama.presentation.screens.actor_item.ActorItemActivity_;
import com.lynx.cinerama.presentation.screens.movie_item.MovieItemActivity_;
import com.lynx.cinerama.presentation.utils.Constants;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 12/8/2016.
 */

@EActivity(R.layout.activity_movies)
@OptionsMenu(R.menu.menu_movies)
public class MoviesActivity extends NavigationActivity implements MoviesContract.MoviesView {

    private MoviesContract.MoviesPresenter presenter;

    @ViewById
    protected Toolbar tbMovies_AM;
    @ViewById
    protected RecyclerView rvGenres_AM;
    @ViewById
    protected ImageView btnAddGenres_AM;
    @ViewById
    protected MaterialSearchView svMovies_AM;
    @ViewById
    protected FrameLayout flSearchContainer_AM;
    @ViewById
    protected TextView tvNoResults_AM;
    @ViewById
    protected RecyclerView rvSearchResult_AM;
    @ViewById
    protected LinearLayout llMainContent_AM;
    @ViewById
    protected LinearLayout llGenres_AM;

    @OptionsMenuItem(R.id.menuLayout_MM)
    protected MenuItem menuLayout_MM;
    @OptionsMenuItem(R.id.menuSearch_MM)
    protected MenuItem menuSearch_MM;

    @Bean
    protected MovieRepository movieRepository;
    @Bean
    protected SearchRepository searchRepository;
    @Bean
    protected MultiSearchAdapter multiSearchAdapter;

    @AfterInject
    protected void initPresenter() {
        new MoviesPresenter(this, movieRepository, searchRepository);
    }

    @AfterViews
    protected void initUI() {
        navigationView.setCheckedItem(R.id.menuItemMovies);
        setupToolbar();
        setupSearch();

        presenter.subscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        svMovies_AM.setMenuItem(menuSearch_MM);
        return super.onCreateOptionsMenu(menu);
    }

    @OptionsItem(R.id.menuLayout_MM)
    protected void clickMenuLayout() {
        presenter.changeDisplayLayout();
    }

    @Override
    public void setTitle(String title) {
        if(getSupportActionBar() != null && !TextUtils.isEmpty(title)) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void setLayout(Constants.LayoutType layoutType) {
        menuLayout_MM.setIcon(layoutType == Constants.LayoutType.LAYOUT_LINEAR
                ? R.drawable.ic_view_list_white_24dp
                : R.drawable.ic_view_module_white_24dp);
        //TODO change layout manager according to layoutType
    }

    @Override
    public void setupMovies(ArrayList<String> movieDHs) {

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
        tvNoResults_AM.setVisibility(resultDHs.size() > 0 ? View.GONE : View.VISIBLE);
        multiSearchAdapter.setListDH(resultDHs);
    }

    @Override
    public void startPersonScreen(int personID) {
        ActorItemActivity_.intent(this)
                .actorID(personID)
                .start();
        finish();
    }

    @Override
    public void startMovieScreen(int movieID) {
        MovieItemActivity_.intent(this)
                .movieID(movieID)
                .start();
        finish();
    }

    @Override
    public void setPresenter(MoviesContract.MoviesPresenter presenter) {
        this.presenter = presenter;
    }

    private void setupToolbar() {
        setSupportActionBar(tbMovies_AM);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, fullView, tbMovies_AM,
                R.string.drawer_open, R.string.drawer_close);
        fullView.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void setupSearch() {
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSearchResult_AM.setLayoutManager(llm);
        rvSearchResult_AM.setAdapter(multiSearchAdapter);

        svMovies_AM.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                llMainContent_AM.setVisibility(View.GONE);
                llGenres_AM.setVisibility(View.GONE);
                flSearchContainer_AM.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {
                llMainContent_AM.setVisibility(View.VISIBLE);
                llGenres_AM.setVisibility(View.VISIBLE);
                flSearchContainer_AM.setVisibility(View.GONE);
            }
        });
        svMovies_AM.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                svMovies_AM.hideKeyboard(flSearchContainer_AM);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() >= 3) {
                    searchRepository.multiSearch(newText)
                            .subscribe(responseMultiSearch -> {
                                presenter.search(newText);
                            });
                } else {
                    multiSearchAdapter.clear();
                    tvNoResults_AM.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        multiSearchAdapter.setOnCardClickListener((view, position, viewType) -> {
            presenter.selectSearchResult(multiSearchAdapter.getItem(position));
            svMovies_AM.closeSearch();
        });
    }
}
