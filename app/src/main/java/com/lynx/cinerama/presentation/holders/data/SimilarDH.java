package com.lynx.cinerama.presentation.holders.data;

import com.lynx.cinerama.data.model.movies.similar.MovieSimilar;
import com.lynx.cinerama.data.model.movies.similar.ShortMovieInfo;
import com.lynx.cinerama.presentation.base.recycler.BaseDH;

/**
 * Created by Lynx on 10/27/2016.
 */

public class SimilarDH extends BaseDH {

    public ShortMovieInfo movieSimilar;

    public SimilarDH(ShortMovieInfo movieSimilar) {
        this.movieSimilar = movieSimilar;
    }
}
