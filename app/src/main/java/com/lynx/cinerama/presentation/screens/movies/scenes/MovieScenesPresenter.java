package com.lynx.cinerama.presentation.screens.movies.scenes;

import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.holders.data.SceneDH;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieScenesPresenter implements MovieScenesContract.MovieScenesPresenter {

    private MovieScenesContract.MovieScencesView view;
    private int movieID;
    private MovieRepository movieRepository;
    private CompositeSubscription compositeSubscription;

    public MovieScenesPresenter(MovieScenesContract.MovieScencesView view, int movieID, MovieRepository movieRepository) {
        this.view = view;
        this.movieID = movieID;
        this.movieRepository = movieRepository;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void setupMovieScenes(List<ImageModel> scenes) {
        ArrayList<SceneDH> sceneDHs = new ArrayList<>();
        for(ImageModel imageModel : scenes)
            sceneDHs.add(new SceneDH(imageModel));
        view.displayScenes(sceneDHs);
    }

    @Override
    public void startSceneGallery(int pos) {
        view.clickScene(pos);
    }

    @Override
    public void subscribe() {
        compositeSubscription.add(
                movieRepository.getMovieScenes(movieID)
                        .subscribe(this::setupMovieScenes)
        );
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
