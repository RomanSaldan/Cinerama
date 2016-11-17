package com.lynx.cinerama.presentation.holders.data;

import com.lynx.cinerama.data.model.actors.credits.ActorCreditCast;
import com.lynx.cinerama.data.model.actors.credits.ActorCreditCrew;
import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by Lynx on 11/17/2016.
 */

public class ActorCreditsDH extends RecyclerDH {
    private String title;
    private ActorCreditCast actorCreditCast;
    private ActorCreditCrew actorCreditCrew;

    public ActorCreditsDH(String title, ActorCreditCast actorCreditCast, ActorCreditCrew actorCreditCrew) {
        this.title = title;
        this.actorCreditCast = actorCreditCast;
        this.actorCreditCrew = actorCreditCrew;
    }

    public String getTitle() {
        return title;
    }

    public ActorCreditCast getActorCreditCast() {
        return actorCreditCast;
    }

    public ActorCreditCrew getActorCreditCrew() {
        return actorCreditCrew;
    }
}
