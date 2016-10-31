package com.lynx.cinerama.data.services;

import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.data.model.movies.similar.MovieSimilar;
import com.lynx.cinerama.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lynx on 10/26/2016.
 */

public interface MovieService {
    @GET(Constants.GET_MOVIE_BY_ID + Constants.API_KEY_URL + Constants.APPEND_TO_MOVIE_RESPONSE)
    Observable<ResponseMovieInfo> getMovieInfo(@Path("id") int id);

    @GET(Constants.GET_SIMILAR_MOVIES + Constants.API_KEY_URL)
    Observable<MovieSimilar> getMovieSimilar(@Path("movie_id") int movieID, @Query("page") int page);
}
