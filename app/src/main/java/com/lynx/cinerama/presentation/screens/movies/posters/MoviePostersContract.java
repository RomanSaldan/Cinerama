package com.lynx.cinerama.presentation.screens.movies.posters;

import android.view.View;

import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.presentation.base.BasePresenter;
import com.lynx.cinerama.presentation.base.BaseView;
import com.lynx.cinerama.presentation.holders.data.PosterDH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lynx on 10/26/2016.
 */

public interface MoviePostersContract {
    interface MoviePosterView extends BaseView<MoviePosterPresenter> {
        void displayPosters(ArrayList<PosterDH> posterDHs);
        void clickPoster(View v, int position);

        void startPosterGalleryScreen(View v, int pos, int movieID);
    }
    interface MoviePosterPresenter extends BasePresenter {
        void setupPosters(List<ImageModel> posters);
        void startPosterGallery(View v, int pos);
    }
}
