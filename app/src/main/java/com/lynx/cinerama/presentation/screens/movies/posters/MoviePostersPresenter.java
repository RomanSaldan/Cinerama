package com.lynx.cinerama.presentation.screens.movies.posters;

import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.presentation.holders.data.PosterDH;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MoviePostersPresenter implements MoviePostersContract.MoviePosterPresenter {

    private MoviePostersContract.MoviePosterView view;
    private ResponseMovieInfo responseMovieInfo;

    public MoviePostersPresenter(MoviePostersContract.MoviePosterView view, ResponseMovieInfo responseMovieInfo) {
        this.view = view;
        this.responseMovieInfo = responseMovieInfo;

        view.setPresenter(this);
    }

    @Override
    public void setupPosters() {
        ArrayList<PosterDH> posterDHs = new ArrayList<>();
        if(responseMovieInfo.images.posters != null && responseMovieInfo.images.posters.size() > 0)
            for(ImageModel imageModel : responseMovieInfo.images.posters)
                posterDHs.add(new PosterDH(imageModel));
        view.displayPosters(posterDHs);
    }

    @Override
    public void startPosterGallery(int pos) {
        view.startPosterGalleryScreen(pos);
    }

    @Override
    public void subscribe() {
        setupPosters();
    }

    @Override
    public void unsubscribe() {

    }
}
