package com.lynx.cinerama.data.model.movies.credits;

import java.io.Serializable;

/**
 * Created by Lynx on 10/26/2016.
 */

public class PersonCast implements Serializable {
    /**
     * cast_id: 406,
     character: "Conference Attendee (uncredited)",
     credit_id: "56db0d319251417a4e002490",
     id: 1586759,
     name: "Francis Brooke",
     order: 139,
     profile_path: "/b0pGKoaPbL3tW6cGZHFTvlG0bbw.jpg"
     */
    public int cast_id;
    public String character;
    public String credit_id;
    public int id;
    public String name;
    public int order;
    public String profile_path;
}
