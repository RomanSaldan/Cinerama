package com.lynx.cinerama.presentation.screens.movies.info.fullscreen_poster;

import android.graphics.Bitmap;

/**
 * Created by Lynx on 10/31/2016.
 */

public class FullscreenPosterPresenter implements FullscreenPosterContract.FullscreenPosterPresenter {

    private FullscreenPosterContract.FullscreenPosterView view;
    private Bitmap imageBitmap;

    public FullscreenPosterPresenter(FullscreenPosterContract.FullscreenPosterView view, Bitmap imageBitmap) {
        this.view = view;
        this.imageBitmap = imageBitmap;

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
//        view.displayProgress(true);
        view.initImage(imageBitmap);
    }

    @Override
    public void unsubscribe() {

    }
}
