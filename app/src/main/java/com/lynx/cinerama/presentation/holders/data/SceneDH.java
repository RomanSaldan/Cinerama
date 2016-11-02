package com.lynx.cinerama.presentation.holders.data;

import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by Lynx on 11/1/2016.
 */

public class SceneDH extends RecyclerDH {
    private ImageModel imageModel;

    public SceneDH(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public ImageModel getImageModel() {
        return imageModel;
    }
}
