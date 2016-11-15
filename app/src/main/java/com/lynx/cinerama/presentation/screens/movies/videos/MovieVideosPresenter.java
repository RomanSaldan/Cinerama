package com.lynx.cinerama.presentation.screens.movies.videos;

import com.lynx.cinerama.data.model.movies.videos.MovieVideo;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.holders.data.VideoDH;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieVideosPresenter implements MovieVideosContract.MovieVideoPresenter {

    private MovieVideosContract.MovieVideoView view;
    private int movieID;
    private MovieRepository movieRepository;
    private CompositeSubscription compositeSubscription;

    public MovieVideosPresenter(MovieVideosContract.MovieVideoView view, int movieID, MovieRepository movieRepository) {
        this.view = view;
        this.movieID = movieID;
        this.movieRepository = movieRepository;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void setupMovieVideos() {
        compositeSubscription.add(
                movieRepository.getMovieVideos(movieID)
                .subscribe(movieVideos -> {
                    view.displayVideos(prepareMovieDHs(movieVideos));
                })
        );
    }

    @Override
    public void startYoutubeVideo(String videoID) {
        view.startFullscreenVideo(videoID);
    }

    @Override
    public void subscribe() {
        setupMovieVideos();
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private ArrayList<VideoDH> prepareMovieDHs(List<MovieVideo> videos) {
        ArrayList<VideoDH> result = new ArrayList<>();
        if(videos != null && videos.size() > 0) {
            for(MovieVideo mv : videos)
                result.add(new VideoDH(mv));
        }
        return result;
    }
}
