package com.lynx.cinerama.data.services;

import com.lynx.cinerama.data.model.actors.ResponseActorInfo;
import com.lynx.cinerama.data.model.actors.profile_tagged_image.ActorTaggedImages;
import com.lynx.cinerama.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lynx on 11/15/2016.
 */

public interface ActorService {

    @GET(Constants.GET_ACTOR_BY_ID + Constants.APPEND_TO_MOVIE_RESPONSE)
    Observable<ResponseActorInfo> getActorInfo(@Path("id") int id);

    @GET(Constants.GET_PERSON_TAGGED_IMAGES)
    Observable<ActorTaggedImages> getPersonScenes(@Path("id") int personID, @Query("page") int page);
}
