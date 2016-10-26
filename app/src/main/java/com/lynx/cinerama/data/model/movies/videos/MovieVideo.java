package com.lynx.cinerama.data.model.movies.videos;

import java.io.Serializable;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieVideo implements Serializable {
    /**
     * id: "533ec6e0c3a3685448008607",
     iso_639_1: "en",
     iso_3166_1: "US",
     key: "iszwuX1AK6A",
     name: "Official Trailer",
     site: "YouTube",
     size: 720,
     type: "Trailer"
     */
    public String id;
    public String iso_639_1;
    public String iso_3166_1;
    public String key;
    public String name;
    public String site;
    public int size;
    public String type;
}
