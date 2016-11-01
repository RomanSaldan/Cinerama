package com.lynx.cinerama.presentation.holders.data;

import com.lynx.cinerama.data.model.movies.credits.PersonCast;
import com.lynx.cinerama.data.model.movies.credits.PersonCrew;
import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by Lynx on 11/1/2016.
 */

public class CreditsDH extends RecyclerDH {
    private String title;
    private PersonCast personCast;
    private PersonCrew personCrew;

    public CreditsDH(String title) {
        this.title = title;
    }

    public CreditsDH(PersonCast personCast) {
        this.personCast = personCast;
    }

    public CreditsDH(PersonCrew personCrew) {
        this.personCrew = personCrew;
    }

    public PersonCast getPersonCast() {
        return personCast;
    }

    public PersonCrew getPersonCrew() {
        return personCrew;
    }

    public String getTitle() {
        return title;
    }
}
