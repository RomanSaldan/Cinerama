package com.lynx.cinerama.presentation.utils;

/**
 * Created by Lynx on 10/26/2016.
 */

public class Constants {
    public static final String BASE_URL                     = "http://api.themoviedb.org/3/";
    public static final String BASE_IMAGE_URL               = "http://image.tmdb.org/t/p/w300";
    public static final String BASE_LARGE_IMAGE_URL         = "http://image.tmdb.org/t/p/w600";
    public static final String API_KEY_TMDB                 = "c7b8800fb43dc8a7ae72d65d93a4767b";

    public static final String API_KEY_YOUTUBE              = "AIzaSyCDD54DU7D-20fdAJ-wratkV8m6QZtXM3Y";
    public static final String URL_THUMBNAIL_PATTERN        = "https://img.youtube.com/vi/%s/mqdefault.jpg";

    public static final String GET_MOVIE_BY_ID              = "movie/{id}";
    public static final String GET_SIMILAR_MOVIES           = "movie/{movie_id}/similar";
    public static final String GET_MOVIE_REVIEWS            = "movie/{movie_id}/reviews";
    public static final String APPEND_TO_MOVIE_RESPONSE     = "?append_to_response=similar,reviews,images,credits,videos";

    public static final String GET_ACTOR_BY_ID              = "person/{id}";
    public static final String APPEND_TO_ACTOR_RESPONSE     = "?append_to_response=credits,images,tagged_images";
    public static final String GET_PERSON_TAGGED_IMAGES     = "person/{id}/tagged_images";
    public static final String IMDB_ACTOR_PREFIX            = "http://www.imdb.com/name/";

    public static final String GET_MULTI_SEARCH             = "search/multi";

    public static final int TEST_MOVIE_ID                   = 18;
    public static final int TEST_ACTOR_ID                   = 11701;

    public static final String LANGUAGE_FILTER              = "en";
    public static final String GALLERY_TYPE_SCREENS         = "Screens";
    public static final String GALLERY_TYPE_POSTERS         = "Posters";
    public static final String GALLERY_TYPE_IMAGES          = "Images";
    public static final String GALLERY_TYPE_ACTOR_SCENES    = "ActorScenes";

    public static final String KEY_DIRECTOR                 = "Director";

    public static final int DELAY_CLICK                     = 600;
    public static final int DELAY_CLICK_ANIMATION           = 1500;

    public static final boolean ENABLE_LEAK_CANARY          = true;
}
