package com.lynx.cinerama.presentation.holders.data;

import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by Lynx on 11/1/2016.
 */

public class PosterDH extends RecyclerDH {
    private ImageModel data;

    public PosterDH(ImageModel data) {
        this.data = data;
    }

    public ImageModel getData() {
        return data;
    }
}
