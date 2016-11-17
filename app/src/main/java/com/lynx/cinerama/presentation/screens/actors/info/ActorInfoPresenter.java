package com.lynx.cinerama.presentation.screens.actors.info;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import com.lynx.cinerama.data.model.actors.ResponseActorInfo;
import com.lynx.cinerama.domain.ActorRepository;
import com.lynx.cinerama.presentation.utils.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 11/16/2016.
 */

public class ActorInfoPresenter implements ActorInfoContract.ActorInfoPresenter {

    private ActorInfoContract.ActorInfoView view;
    private ActorRepository actorRepository;
    private int actorID;
    private CompositeSubscription compositeSubscription;

    private ResponseActorInfo info;
    private Bitmap bitmap;

    public ActorInfoPresenter(ActorInfoContract.ActorInfoView view, ActorRepository actorRepository, int actorID) {
        this.view = view;
        this.actorRepository = actorRepository;
        this.actorID = actorID;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        compositeSubscription.add(
                actorRepository.getActorInfo(actorID)
                        .subscribe(responseActorInfo -> {
                            info = responseActorInfo;
                                    setupActorInfo();
                        },
                                t -> Log.d("err", t.getMessage()))
        );
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void startActorWebpage() {
        if(!TextUtils.isEmpty(info.homepage))
            view.displayActorWebpage(info.homepage);
    }

    @Override
    public void startActorImdbPage() {
        if(!TextUtils.isEmpty(info.imdb_id))
            view.displayActorImdbPage(Constants.IMDB_ACTOR_PREFIX + info.imdb_id);
    }

    @Override
    public void startFullscreenImage() {
        view.displayFullscreenImage(bitmap);
    }

    @Override
    public void setFullscreenBitmap(Bitmap b) {
        bitmap = b;
    }

    private void setupActorInfo() {
        if(!TextUtils.isEmpty(info.name))
            view.displayActorName(info.name);
        if(!TextUtils.isEmpty(info.profile_path))
            view.displayActorImage(info.profile_path);
        if(info.also_known_as != null && info.also_known_as.size() > 0)
            view.displayKnownAs(TextUtils.join(", ", info.also_known_as));
        view.enableWebButton(!TextUtils.isEmpty(info.homepage));
        view.enableImdbButton(!TextUtils.isEmpty(info.imdb_id));
        if(!TextUtils.isEmpty(info.place_of_birth))
            view.displayBirthplace(info.place_of_birth);
        if(!TextUtils.isEmpty(info.birthday))
            view.displayBirthdate(getBirthdate());
        if(!TextUtils.isEmpty(info.biography))
            view.displayBiography(info.biography);
    }

    private String getBirthdate() {
        if(!TextUtils.isEmpty(info.birthday)) {
            Date dob = null;
            Date dod = null;
            int age = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dob = sdf.parse(info.birthday);
                dod = TextUtils.isEmpty(info.deathday) ? new Date(System.currentTimeMillis()) : sdf.parse(info.deathday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calDob = Calendar.getInstance();
            calDob.setTime(dob);
            Calendar calDod = Calendar.getInstance();
            calDod.setTime(dod);
            age = calDod.get(Calendar.YEAR) - calDob.get(Calendar.YEAR);
            return String.format("%s %s (%d years)", info.birthday, info.deathday, age);
        } else
            return "";
    }
}
