package com.lynx.cinerama.data.model.movies.reviews;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieReviews implements Serializable {
    public int page;
    public int total_pages;
    public int total_results;
    public ArrayList<MovieReview> results;
}
