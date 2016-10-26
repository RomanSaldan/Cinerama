package com.lynx.cinerama.data.model.movies.gallery;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieGallery implements Serializable {
    public int id;
    public ArrayList<ImageModel> backdrops;
    public ArrayList<ImageModel> posters;
}
