package com.lynx.cinerama.presentation.screens.movies.videos;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.WorkerThread;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.lynx.cinerama.R;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.adapters.VideosAdapter;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.holders.data.VideoDH;
import com.lynx.cinerama.presentation.screens.NavigationActivity;
import com.lynx.cinerama.presentation.screens.movies.MoviesActivity;
import com.lynx.cinerama.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

@EFragment(R.layout.fragment_movie_videos)
public class MovieVideosFragment extends BaseFragment<MoviesActivity> implements MovieVideosContract.MovieVideoView {

    private MovieVideosContract.MovieVideoPresenter presenter;
    private GridLayoutManager glm;

    @FragmentArg
    protected int movieID;

    @Bean
    protected VideosAdapter videosAdapter;

    @Bean
    protected MovieRepository movieRepository;

    @ViewById
    protected RecyclerView rvVideos_FMV;


    @AfterInject
    protected void initPresenter() {
        new MovieVideosPresenter(this, movieID, movieRepository);
    }

    @AfterViews
    protected void initUI() {
        int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1;
        glm = new GridLayoutManager(getActivity(), spanCount, LinearLayoutManager.VERTICAL, false);
        rvVideos_FMV.setLayoutManager(glm);
        rvVideos_FMV.setAdapter(videosAdapter);
        videosAdapter.setOnCardClickListener((view, position, viewType) -> onVideoClicked(videosAdapter.getItem(position).getMovieVideo().key));

        presenter.subscribe();
    }

    @Override
    public void displayVideos(ArrayList<VideoDH> listDHs) {
        videosAdapter.setListDH(listDHs);
    }

    @Override
    public void onVideoClicked(String videoID) {
        presenter.startYoutubeVideo(videoID);
    }

    @WorkerThread
    @Override
    public void startFullscreenVideo(String videoID) {
        mActivity.displayProgress(true);
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(getActivity(), Constants.API_KEY_YOUTUBE, videoID);
        getActivity().startActivity(intent);

    }

    @Override
    public void setPresenter(MovieVideosContract.MovieVideoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null)
            presenter.unsubscribe();
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.displayProgress(false);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int spanCount = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1;
        glm.setSpanCount(spanCount);
    }

}
