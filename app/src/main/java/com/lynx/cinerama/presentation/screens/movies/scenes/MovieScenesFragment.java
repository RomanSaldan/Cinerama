package com.lynx.cinerama.presentation.screens.movies.scenes;

import android.content.res.Configuration;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lynx.cinerama.R;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.adapters.ScenesAdapter;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.holders.data.SceneDH;
import com.lynx.cinerama.presentation.screens.gallery.FullscreenImageActivity_;
import com.lynx.cinerama.presentation.screens.movies.MoviesActivity;
import com.lynx.cinerama.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

@EFragment(R.layout.fragemnt_movie_scenes)
public class MovieScenesFragment extends BaseFragment<MoviesActivity> implements MovieScenesContract.MovieScencesView {

    private MovieScenesContract.MovieScenesPresenter presenter;
    private GridLayoutManager glm;

    @FragmentArg
    protected int movieID;

    @Bean
    protected MovieRepository movieRepository;

    @Bean
    protected ScenesAdapter scenesAdapter;

    @ViewById
    protected RecyclerView rvScenes_FMS;

    @AfterInject
    protected void initPresenter() {
        new MovieScenesPresenter(this, movieID, movieRepository);
    }

    @AfterViews
    protected void initUI() {
        int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2;
        glm = new GridLayoutManager(getActivity(), spanCount, LinearLayoutManager.VERTICAL, false);
        rvScenes_FMS.setLayoutManager(glm);
        rvScenes_FMS.setAdapter(scenesAdapter);
        scenesAdapter.setOnCardClickListener((view, position, viewType) -> {
            clickScene(view, position);
        });

        presenter.subscribe();
    }

    @Override
    public void displayScenes(ArrayList<SceneDH> sceneDHs) {
        scenesAdapter.setListDH(sceneDHs);
    }

    @Override
    public void clickScene(View v, int pos) {
        presenter.startSceneGallery(v, pos);
    }

    @Override
    public void displaySceneGallery(View v, int pos, int movieID) {
        View vv = glm.findContainingItemView(v).findViewById(R.id.ivMovieScene_LIS);

        vv.setTransitionName("gallery" + pos);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), vv, "gallery" + pos);

        FullscreenImageActivity_.intent(getActivity())
                .movieID(movieID)
                .currentPosition(pos)
                .galleryType(Constants.GALLERY_TYPE_SCREENS)
                .withOptions(options.toBundle())
                .start();
    }


    @Override
    public void setPresenter(MovieScenesContract.MovieScenesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int spanCount = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2;
        glm.setSpanCount(spanCount);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null)
            presenter.unsubscribe();
    }
}
