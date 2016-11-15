package com.lynx.cinerama.presentation.screens.movies.videos;

import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.VideoDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public interface MovieVideosContract {
    interface MovieVideoView extends BaseView<MovieVideoPresenter> {
        void displayVideos(ArrayList<VideoDH> listDHs);
        void onVideoClicked(String videoID);
        void startFullscreenVideo(String videoID);
    }
    interface MovieVideoPresenter extends BasePresenter {
        void setupMovieVideos();
        void startYoutubeVideo(String videoID);
    }
}
