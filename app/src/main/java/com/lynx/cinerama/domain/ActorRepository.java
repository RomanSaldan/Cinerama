package com.lynx.cinerama.domain;

import com.lynx.cinerama.data.api.Rest;
import com.lynx.cinerama.data.model.actors.ResponseActorInfo;
import com.lynx.cinerama.data.model.actors.credits.ActorCredits;
import com.lynx.cinerama.data.model.actors.profile_tagged_image.ActorTaggedImages;
import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.data.services.ActorService;

import org.androidannotations.annotations.EBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
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

    public ActorRepository() {
        actorService = Rest.getInstance().getActorService();
    }

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
                .flatMap(responseActorInfo -> {
                    Collections.sort(responseActorInfo.credits.cast, (actorCreditCast, t1) -> {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date d1 = sdf.parse(actorCreditCast.release_date);
                            Date d2 = sdf.parse(t1.release_date);
                            return d2.after(d1) ? 1 : -1;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return -1;
                    });
                    Collections.sort(responseActorInfo.credits.crew, (actorCreditCrew, t1) -> {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date d1 = sdf.parse(actorCreditCrew.release_date);
                            Date d2 = sdf.parse(t1.release_date);
                            return d2.after(d1) ? 1 : -1;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return -1;
                    });
                   return Observable.just(responseActorInfo.credits);
                });
    }

    public Observable<List<ImageModel>> getActorImages(int actorID) {
        return getActorInfo(actorID)
                .flatMap(responseActorInfo -> Observable.just(responseActorInfo.images.profiles))
                .flatMap(Observable::from)
                .filter(ImageModel::getIso_639_1Valid)
                .toList();
    }

    public Observable<ActorTaggedImages> getActorScenes(int actorID, int page) {
        return getNetworkObservable(actorService.getPersonScenes(actorID, page));
    }

}
