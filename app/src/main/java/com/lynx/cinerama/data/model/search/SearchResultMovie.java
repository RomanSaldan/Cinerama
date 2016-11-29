package com.lynx.cinerama.data.model.search;

/**
 * Created by Lynx on 11/29/2016.
 */

public class SearchResultMovie extends SearchResultCommon {
    /**
     *  {
     "poster_path": "/jksXcyWURdXMcGgShL0aMSR7g8d.jpg",
     "adult": false,
     "overview": "Bradley vs. Provodnikov for the WBO Welterweight title was fought on March 16th, 2012 at The Home Depot Center in Carson, California, USA.",
     "release_date": "2013-03-16",
     "original_title": "Bradley vs. Provodnikov",
     "genre_ids": [],
     "id": 179821,
     "media_type": "movie",
     "original_language": "en",
     "title": "Bradley vs. Provodnikov",
     "backdrop_path": "/jzqQCuTQyZAglGMTV8fSE3HKpma.jpg",
     "popularity": 1.001714,
     "vote_count": 0,
     "video": true,
     "vote_average": 0
     },
     */
    public String media_type;

    public String poster_path;
    public boolean adult;
    public String overview;
    public String release_date;
    public String original_title;
    public int[] genre_ids;
    public int id;
    public String original_language;
    public String title;
    public String backdrop_path;
    public float popularity;
    public int vote_count;
    public int vote_average;
    public boolean video;
}
