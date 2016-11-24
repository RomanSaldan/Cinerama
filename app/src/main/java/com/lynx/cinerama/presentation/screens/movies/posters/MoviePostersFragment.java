package com.lynx.cinerama.presentation.screens.movies.posters;

import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.adapters.PostersAdapter;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.holders.data.PosterDH;
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

@EFragment(R.layout.fragment_movie_posters)
public class MoviePostersFragment extends BaseFragment<MoviesActivity> implements MoviePostersContract.MoviePosterView {

    private MoviePostersContract.MoviePosterPresenter presenter;
    private GridLayoutManager glm;

    @FragmentArg
    protected int movieID;

    @ViewById
    protected RecyclerView rvPosters_FMP;

    @Bean
    protected MovieRepository movieRepository;

    @Bean
    protected PostersAdapter postersAdapter;

    @AfterInject
    protected void initPresenter() {
        new MoviePostersPresenter(this, movieID, movieRepository);
    }

    @AfterViews
    protected void initUI() {
        int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 3;
        glm = new GridLayoutManager(getActivity(), spanCount, LinearLayoutManager.VERTICAL, false);
        rvPosters_FMP.setLayoutManager(glm);
        rvPosters_FMP.setAdapter(postersAdapter);
        postersAdapter.setOnCardClickListener((view, position, viewType) -> clickPoster(view, position));

        presenter.subscribe();
    }

    @Override
    public void displayPosters(ArrayList<PosterDH> posterDHs) {
        postersAdapter.setListDH(posterDHs);
    }

    @Override
    public void clickPoster(View v, int position) {
        presenter.startPosterGallery(v, position);
    }

    @Override
    public void startPosterGalleryScreen(View v, int pos, int movieID) {
        View vv = glm.findContainingItemView(v).findViewById(R.id.ivPoster_LIP);

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            vv.setTransitionName("gallery" + pos);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), vv, "gallery" + pos);

        FullscreenImageActivity_.intent(getActivity())
                .movieID(movieID)
                .currentPosition(pos)
                .galleryType(Constants.GALLERY_TYPE_POSTERS)
                .withOptions(options.toBundle())
                .start();

    }

    @Override
    public void setPresenter(MoviePostersContract.MoviePosterPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int spanCount = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 3;
        glm.setSpanCount(spanCount);
    }
}
