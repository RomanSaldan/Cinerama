package com.lynx.cinerama.presentation.screens.gallery;

import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 11/2/2016.
 */

public interface FullscreenImageContract {
    interface FullscreenImageView extends BaseView<FullscreenImagePresenter> {
        void displayGallery(List<ImageModel> imageModels);
        void displayIndicator(int current, int total);
        void displayTitle(String title);
    }
    interface FullscreenImagePresenter extends BasePresenter {
        void setupGalleryItems();
    }
}
