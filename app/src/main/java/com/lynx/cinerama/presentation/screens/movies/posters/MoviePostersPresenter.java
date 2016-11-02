package com.lynx.cinerama.presentation.screens.movies.posters;

import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.holders.data.PosterDH;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MoviePostersPresenter implements MoviePostersContract.MoviePosterPresenter {

    private MoviePostersContract.MoviePosterView view;
    private int movieID;
    private MovieRepository movieRepository;
    private CompositeSubscription compositeSubscription;

    public MoviePostersPresenter(MoviePostersContract.MoviePosterView view, int movieID, MovieRepository movieRepository) {
        this.view = view;
        this.movieID = movieID;
        this.movieRepository = movieRepository;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void setupPosters(List<ImageModel> posters) {
        ArrayList<PosterDH> posterDHs = new ArrayList<>();
        if(posters != null && posters.size() > 0)
            for(ImageModel imageModel : posters)
                posterDHs.add(new PosterDH(imageModel));
        view.displayPosters(posterDHs);
    }

    @Override
    public void startPosterGallery(int pos) {
        view.startPosterGalleryScreen(pos, movieID);
    }

    @Override
    public void subscribe() {
        compositeSubscription.add(
                movieRepository.getMoviePosters(movieID)
                    .subscribe(this::setupPosters)
        );
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
