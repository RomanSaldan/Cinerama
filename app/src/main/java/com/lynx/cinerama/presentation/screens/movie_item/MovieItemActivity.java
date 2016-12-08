package com.lynx.cinerama.presentation.screens.movie_item;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.domain.SearchRepository;
import com.lynx.cinerama.presentation.adapters.MoviesTabAdapter;
import com.lynx.cinerama.presentation.adapters.MultiSearchAdapter;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;
import com.lynx.cinerama.presentation.screens.NavigationActivity;
import com.lynx.cinerama.presentation.screens.actor_item.ActorItemActivity_;
import com.lynx.cinerama.presentation.utils.Constants;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lynx on 10/26/2016.
 */

@EActivity(R.layout.activity_movie_item)
@OptionsMenu(R.menu.menu_search)
public class MovieItemActivity extends NavigationActivity implements MovieItemContract.MoviesView {

    private MovieItemContract.MoviesPresenter presenter;

    @Extra
    public int movieID;

    @Bean
    protected MovieRepository movieRepository;
    @Bean
    protected SearchRepository searchRepository;
    @Bean
    protected MultiSearchAdapter multiSearchAdapter;

    @ViewById
    protected ImageView ivCollapsed_AMI;
    @ViewById
    protected MaterialSearchView svMovies_AMI;
    @ViewById
    protected Toolbar toolbar_AMI;
    @ViewById
    protected TabLayout tabLayout_AMI;
    @ViewById
    protected ViewPager viewpager_AMI;
    @ViewById
    protected CollapsingToolbarLayout collapsingToolbar_AMI;
    @ViewById
    protected CoordinatorLayout mainContent_AMI;
    @ViewById
    protected FrameLayout flSearchContainer_AMI;
    @ViewById
    protected RecyclerView rvSearchResult_AMI;
    @ViewById
    protected TextView tvNoResults_AMI;

    @StringRes(R.string.tab_title_info)
    protected String tabTitleInfo;
    @StringRes(R.string.tab_title_cast)
    protected String tabTitleCast;
    @StringRes(R.string.tab_title_posters)
    protected String tabTitlePosters;
    @StringRes(R.string.tab_title_scenes)
    protected String tabTitleScenes;
    @StringRes(R.string.tab_title_videos)
    protected String tabTitleVideos;

    @OptionsMenuItem(R.id.action_menu_search)
    protected MenuItem menuItemSearch;

    @AfterInject
    protected void initPresenter() {
        new MovieItemPresenter(this, movieRepository, searchRepository, movieID == 0 ? Constants.TEST_MOVIE_ID : movieID);
    }

    @AfterViews
    protected void initUI() {
        navigationView.setCheckedItem(R.id.menuItemMovies);
        setupToolbar();
        presenter.subscribe();

        setupSearch();
    }

    @Override
    public void setPresenter(MovieItemContract.MoviesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar_AMI);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, fullView, toolbar_AMI,
                R.string.drawer_open, R.string.drawer_close);
        fullView.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public void setTitle(String title) {
        collapsingToolbar_AMI.setTitle(title);
    }

    @Override
    public void setupBottomBar() {
        HashMap<String, Integer> tabsMap = new HashMap<>();
        tabsMap.put(tabTitleInfo, R.drawable.selector_tab_movie_info);
        tabsMap.put(tabTitleCast, R.drawable.selector_tab_movie_cast);
        tabsMap.put(tabTitleScenes, R.drawable.selector_tab_movie_scenes);
        tabsMap.put(tabTitlePosters, R.drawable.selector_tab_movie_posters);
        tabsMap.put(tabTitleVideos, R.drawable.selector_tab_movie_trailers);

        for (int i = 0; i < tabLayout_AMI.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout_AMI.getTabAt(i);
            if (tabsMap.keySet().contains(tab.getText().toString())) {
                tab.setIcon(tabsMap.get(tab.getText().toString()));
                tab.setText("");
            }
        }
    }

    @Override
    public void setBackdropImage(String backdropPath) {
        Glide.with(this).load(Constants.BASE_LARGE_IMAGE_URL + backdropPath)
                .centerCrop()
                .into(ivCollapsed_AMI);
    }

    @Override
    public void setupMovieInfo(ResponseMovieInfo responseMovieInfo) {
        MoviesTabAdapter tabAdapter = new MoviesTabAdapter(this, getSupportFragmentManager(), responseMovieInfo);
        viewpager_AMI.setAdapter(tabAdapter);
        tabLayout_AMI.setupWithViewPager(viewpager_AMI);
    }

    @Override
    public void refreshMovieInfo(int movieID) {
        this.movieID = movieID;
        initPresenter();
        presenter.subscribe();
    }

    @Override
    public void openCast() {
        viewpager_AMI.setCurrentItem(1, true);
    }

    @Override
    public void displaySearchResults(ArrayList<SearchResultDH> resultDHs) {
        tvNoResults_AMI.setVisibility(resultDHs.size() > 0 ? View.GONE : View.VISIBLE);
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
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.unsubscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        svMovies_AMI.setMenuItem(menuItemSearch);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupSearch() {
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSearchResult_AMI.setLayoutManager(llm);
        rvSearchResult_AMI.setAdapter(multiSearchAdapter);

        svMovies_AMI.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                tabLayout_AMI.setVisibility(View.GONE);
                mainContent_AMI.setVisibility(View.GONE);
                flSearchContainer_AMI.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {
                tabLayout_AMI.setVisibility(View.VISIBLE);
                mainContent_AMI.setVisibility(View.VISIBLE);
                flSearchContainer_AMI.setVisibility(View.GONE);
            }
        });
        svMovies_AMI.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                svMovies_AMI.hideKeyboard(flSearchContainer_AMI);
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
                    tvNoResults_AMI.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        multiSearchAdapter.setOnCardClickListener((view, position, viewType) -> {
            presenter.selectSearchResult(multiSearchAdapter.getItem(position));
            svMovies_AMI.closeSearch();
        });
    }
}
