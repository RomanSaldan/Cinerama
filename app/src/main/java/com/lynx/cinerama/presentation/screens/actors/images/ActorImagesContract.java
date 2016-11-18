package com.lynx.cinerama.presentation.screens.actors.images;

import android.view.View;

import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.PosterDH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 11/16/2016.
 */

public interface ActorImagesContract {
    interface ActorImagesView extends BaseView<ActorImagesPresenter> {
        void displayActorImages(ArrayList<PosterDH> imagesDHs);
        void clickImage(View v, int position);

        void startImageGalleryScreen(View v, int pos, int actorID);
    }
    interface ActorImagesPresenter extends BasePresenter {
        void setupImages(List<ImageModel> images);
        void startImageGallery(View v, int pos);
    }
}
