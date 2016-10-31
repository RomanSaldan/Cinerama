package com.lynx.cinerama.presentation.utils;

/**
 * Created by Lynx on 10/26/2016.
 */

public class Constants {
    public static final String BASE_URL                     = "http://api.themoviedb.org/3/";
    public static final String BASE_IMAGE_URL               = "http://image.tmdb.org/t/p/w300";
    public static final String API_KEY_URL                  = "?api_key=c7b8800fb43dc8a7ae72d65d93a4767b";

    public static final String API_KEY_YOUTUBE              = "AIzaSyAlzibiO43wGa4XQFuIvr3aBwS1VC-d4bs";

    public static final String GET_MOVIE_BY_ID              = "movie/{id}";
    public static final String GET_SIMILAR_MOVIES           = "movie/{movie_id}/similar";
    public static final String APPEND_TO_MOVIE_RESPONSE     = "&append_to_response=similar,reviews,images,credits,videos";

    public static final int TEST_MOVIE_ID                   = 18;
    public static final String LANGUAGE_FILTER              = "en";
    public static final String GALLERY_TYPE_SCREENS         = "Screens";
    public static final String GALLERY_TYPE_POSTERS         = "Posters";

    public static final String KEY_DIRECTOR                 = "Director";

    public static final int DELAY_CLICK                     = 600;

    public static final boolean ENABLE_LEAK_CANARY          = true;
}
