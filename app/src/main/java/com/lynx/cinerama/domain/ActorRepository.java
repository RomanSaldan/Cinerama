package com.lynx.cinerama.domain;

import com.lynx.cinerama.data.model.actors.ResponseActorInfo;
import com.lynx.cinerama.data.model.actors.credits.ActorCredits;
import com.lynx.cinerama.data.model.actors.profile_image.ActorProfiles;
import com.lynx.cinerama.data.model.actors.profile_image.ProfileImage;
import com.lynx.cinerama.data.model.actors.profile_tagged_image.ActorTaggedImages;
import com.lynx.cinerama.data.services.ActorService;

import org.androidannotations.annotations.EBean;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 11/15/2016.
 */

@EBean(scope = EBean.Scope.Singleton)
public class ActorRepository {

    private ActorService actorService;

    private int actorID = -1;

    private ResponseActorInfo cachedActorInfo;

    private <T> Observable<T> getNetworkObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseActorInfo> getActorInfo(int id) {
        Observable<ResponseActorInfo> actorInfoObservable;
        if(cachedActorInfo == null || id != actorID) {
            actorInfoObservable = getNetworkObservable(actorService.getActorInfo(id))
                    .flatMap(responseMovieInfo -> {
                        cachedActorInfo = responseMovieInfo;
                        return Observable.just(responseMovieInfo);
                    });
            actorID = id;
        } else {
            actorInfoObservable = Observable.just(cachedActorInfo);
        }
        return actorInfoObservable;
    }

    public Observable<ActorCredits> getActorCredits(int actorID) {
        return getActorInfo(actorID)
                .flatMap(responseActorInfo -> Observable.just(responseActorInfo.movie_credits));
    }

    public Observable<List<ProfileImage>> getActorImages(int actorID) {
        return getActorInfo(actorID)
                .flatMap(responseActorInfo -> Observable.just(responseActorInfo.images.profiles))
                .flatMap(Observable::from)
                .filter(ProfileImage::getIso_639_1Valid)
                .toList();
    }

    public Observable<ActorTaggedImages> getActorScenes(int actorID, int page) {
        return getNetworkObservable(actorService.getPersonScenes(actorID, page));
    }

}
