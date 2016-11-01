package com.lynx.cinerama.presentation.holders.data;

import com.lynx.cinerama.data.model.movies.similar.MovieSimilar;
import com.lynx.cinerama.data.model.movies.similar.ShortMovieInfo;
import com.lynx.cinerama.presentation.base.recycler.BaseDH;
import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by Lynx on 10/27/2016.
 */

public class SimilarDH extends RecyclerDH {

    public ShortMovieInfo movieSimilar;

    public SimilarDH(ShortMovieInfo movieSimilar) {
        this.movieSimilar = movieSimilar;
    }
}
