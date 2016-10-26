package com.lynx.cinerama.data.model.movies.credits;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieCredits implements Serializable {
    public ArrayList<PersonCast> cast;
    public ArrayList<PersonCrew> crew;
}
