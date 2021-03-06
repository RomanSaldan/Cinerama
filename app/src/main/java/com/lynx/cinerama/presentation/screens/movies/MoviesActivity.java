package com.lynx.cinerama.presentation.screens.movies;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.adapters.MoviesTabAdapter;
import com.lynx.cinerama.presentation.screens.NavigationActivity;
import com.lynx.cinerama.presentation.utils.Constants;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.HashMap;

/**
 * Created by Lynx on 10/26/2016.
 */

@EActivity(R.layout.activity_movies)
@OptionsMenu(R.menu.menu_search)
public class MoviesActivity extends NavigationActivity implements MoviesContract.MoviesView {

    private MoviesContract.MoviesPresenter presenter;

    @Bean
    protected MovieRepository movieRepository;

    @ViewById
    protected ImageView ivCollapsed_AM;

    @ViewById
    protected MaterialSearchView svMovies_AM;

    @ViewById
    protected Toolbar toolbar_AM;

    @ViewById
    protected TabLayout tabLayout_AM;

    @ViewById
    protected ViewPager viewpager_AM;

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

    @AfterInject
    protected void initPresenter() {

        new MoviesPresenter(this, movieRepository, Constants.TEST_MOVIE_ID);
    }

    @AfterViews
    protected void initUI() {
        navigationView.setCheckedItem(R.id.menuItemMovies);

        presenter.subscribe();
    }

    @Override
    public void setPresenter(MoviesContract.MoviesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setupToolbar(String title) {
        setSupportActionBar(toolbar_AM);
        getSupportActionBar().setTitle("");

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,  fullView, toolbar_AM,
                R.string.drawer_open, R.string.drawer_close);
        fullView.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public void setupBottomBar() {
        HashMap<String,Integer> tabsMap = new HashMap<>();
        tabsMap.put(tabTitleInfo, R.drawable.selector_tab_info);
        tabsMap.put(tabTitleCast, R.drawable.selector_tab_cast);
        tabsMap.put(tabTitleScenes, R.drawable.selector_tab_scenes);
        tabsMap.put(tabTitlePosters, R.drawable.selector_tab_posters);
        tabsMap.put(tabTitleVideos, R.drawable.selector_tab_trailers);

        for(int i = 0; i < tabLayout_AM.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout_AM.getTabAt(i);
            if(tabsMap.keySet().contains(tab.getText().toString())) {
                tab.setIcon(tabsMap.get(tab.getText().toString()));
                tab.setText("");
            }
        }
    }

    @Override
    public void setBackdropImage(String backdropPath) {
        Glide.with(this).load(Constants.BASE_IMAGE_URL + backdropPath)
                .centerCrop()
                .into(ivCollapsed_AM);
    }

    @Override
    public void setupMovieInfo(ResponseMovieInfo responseMovieInfo) {
        MoviesTabAdapter tabAdapter = new MoviesTabAdapter(getSupportFragmentManager(), responseMovieInfo);
        viewpager_AM.setAdapter(tabAdapter);
        tabLayout_AM.setupWithViewPager(viewpager_AM);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null) presenter.unsubscribe();
    }
}
