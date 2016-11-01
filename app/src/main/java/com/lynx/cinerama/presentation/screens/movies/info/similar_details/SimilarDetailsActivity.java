package com.lynx.cinerama.presentation.screens.movies.info.similar_details;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.lynx.cinerama.R;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.adapters.SimilarAdapter;
import com.lynx.cinerama.presentation.listeners.EndlessRecyclerViewScrollListener;
import com.lynx.cinerama.presentation.holders.data.SimilarDH;
import com.lynx.cinerama.presentation.screens.movies.MoviesActivity_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/28/2016.
 */

@EActivity(R.layout.activity_similar_details)
public class SimilarDetailsActivity extends AppCompatActivity implements SimilarDetailsContract.SimilarDetailsView {

    private SimilarDetailsContract.SimilarDetailsPresenter presenter;

    private GridLayoutManager glm;

    @Extra
    protected int movieID;

    @Extra
    protected String movieTitle;

    @Bean
    protected MovieRepository movieRepository;

    @Bean
    protected SimilarAdapter similarAdapter;

    @ViewById
    protected Toolbar toolbar_ASD;
    @ViewById
    protected RecyclerView rvSimilar_ASD;

    @AfterInject
    protected void initPresenter() {
        new SimilarDetailsPresenter(this, movieID, movieTitle, movieRepository);
    }

    @AfterViews
    protected void initUI() {
        int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE? 4 : 3;
        glm = new GridLayoutManager(this, spanCount, LinearLayoutManager.VERTICAL, false);
        rvSimilar_ASD.setLayoutManager(glm);
        rvSimilar_ASD.setAdapter(similarAdapter);
        rvSimilar_ASD.addOnScrollListener(new EndlessRecyclerViewScrollListener(glm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.loadMoreSimilars(page);
            }
        });
        similarAdapter.setOnCardClickListener((view, position, viewType) -> {presenter.startMovieInfoScreen(similarAdapter.getItem(position).movieSimilar.id);});

        presenter.subscribe();
    }

    @Override
    public void setupToolbar(String movieTitle) {
        toolbar_ASD.setTitle(movieTitle);
        setSupportActionBar(toolbar_ASD);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void displayMoreSimilars(ArrayList<SimilarDH> similarDHs) {
        similarAdapter.addListDH(similarDHs);
    }

    @Override
    public void onMovieClicked(int movieID) {
        presenter.startMovieInfoScreen(movieID);
    }

    @Override
    public void displayMovieInfoScreen(int movieID) {
        finish();
        MoviesActivity_.intent(this)
                .movieID(movieID)
                .start();
    }

    @Override
    public void setPresenter(SimilarDetailsContract.SimilarDetailsPresenter presenter) {
        this.presenter = presenter;
    }
    @OptionsItem(android.R.id.home)
    protected void homeSelected() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null)
            presenter.unsubscribe();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int spanCount = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE? 4 : 3;
        glm.setSpanCount(spanCount);
    }
}
