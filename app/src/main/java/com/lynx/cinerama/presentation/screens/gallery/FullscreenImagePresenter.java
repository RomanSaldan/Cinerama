package com.lynx.cinerama.presentation.screens.gallery;

import com.lynx.cinerama.domain.ActorRepository;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.utils.Constants;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 11/2/2016.
 */

public class FullscreenImagePresenter implements FullscreenImageContract.FullscreenImagePresenter {

    private FullscreenImageContract.FullscreenImageView view;
    private int movieID;
    private String galleryType;
    private MovieRepository movieRepository;
    private CompositeSubscription compositeSubscription;

    private int actorID;
    private ActorRepository actorRepository;

    private boolean isSupportViewsVisible = true;

    public FullscreenImagePresenter(FullscreenImageContract.FullscreenImageView view, int movieID, String galleryType, MovieRepository movieRepository) {
        this.view = view;
        this.movieID = movieID;
        this.galleryType = galleryType;
        this.movieRepository = movieRepository;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    public FullscreenImagePresenter(FullscreenImageContract.FullscreenImageView view, int actorID, String galleryType, ActorRepository actorRepository) {
        this.view = view;
        this.galleryType = galleryType;
        this.actorID = actorID;
        this.actorRepository = actorRepository;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void setupGalleryItems() {
        switch (galleryType) {
            case Constants.GALLERY_TYPE_POSTERS:
                compositeSubscription.add(
                        movieRepository.getMoviePosters(movieID)
                            .subscribe(view::displayGallery)
                );
                break;
            case Constants.GALLERY_TYPE_SCREENS:
                compositeSubscription.add(
                        movieRepository.getMovieScenes(movieID)
                            .subscribe(view::displayGallery)
                );
                break;
            case Constants.GALLERY_TYPE_IMAGES:
                compositeSubscription.add(
                        actorRepository.getActorImages(actorID)
                        .subscribe(view::displayGallery)
                );
        }
    }

    @Override
    public void back() {
        view.close();
    }

    @Override
    public void screenClicked() {
        isSupportViewsVisible = !isSupportViewsVisible;
        view.showSupportViews(isSupportViewsVisible);
    }

    @Override
    public void subscribe() {
        setupGalleryItems();
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }
}
