package com.lynx.cinerama.presentation.screens.movie_item.info.fullscreen_poster;

import android.graphics.Bitmap;

import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;

/**
 * Created by Lynx on 10/31/2016.
 */

public interface FullscreenPosterContract {
    interface FullscreenPosterView extends BaseView<FullscreenPosterPresenter> {
        void initImage(Bitmap imageBitmap);
        void displayProgress(boolean isShown);
    }
    interface FullscreenPosterPresenter extends BasePresenter {

    }
}
