package com.lynx.cinerama.data.model.movies.gallery;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by Lynx on 10/26/2016.
 */

public class ImageModel implements Serializable {
    /**
     * aspect_ratio: 1.77777777777778,
     file_path: "/6Ue2NvfsZemoVIw9PQx0CELzSK7.jpg",
     height: 1080,
     iso_639_1: null,
     vote_average: 0,
     vote_count: 0,
     width: 1920
     */
    public float aspect_ratio;
    public String file_path;
    public int height;
    public int width;
    public String iso_639_1;
    public float vote_average;
    public int vote_count;

    public boolean getIso_639_1Valid() {
        return TextUtils.isEmpty(iso_639_1) || iso_639_1.equalsIgnoreCase("en");
    }
}
