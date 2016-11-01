package com.lynx.cinerama.presentation.holders.data;

import com.lynx.cinerama.data.model.movies.reviews.MovieReview;
import com.lynx.cinerama.presentation.base.recycler.BaseDH;
import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by Lynx on 10/28/2016.
 */

public class ReviewDH extends RecyclerDH {

    public MovieReview movieReview;

    public ReviewDH(MovieReview movieReview) {
        this.movieReview = movieReview;
    }
}
