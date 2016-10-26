package com.lynx.cinerama.data.model.movies.similar;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class ShortMovieInfo implements Serializable {
    /**
     * adult: false,
     backdrop_path: "/uLLcFSd7fpea7wtSppUClq6cmpe.jpg",
     genre_ids: [
     35,
     18
     ],
     id: 37265,
     original_language: "it",
     original_title: "Così fan tutte",
     overview: "Diana is a Roman wife happily married to sympathetic Paolo but she is keen on playing benign games of seduction with other men while resisting the advances of chic lingerie shop owner Silvio and she narrates her adventures to Paolo in order to stimulate their otherwise monotonous sexual life. However, under the influence of her lesbian friend Antonietta and raunchy sister Nadia, Diana starts to move the ongoings further while Paolo is still prone to believing that events narrated by her are merely fantasies. Nevertheless, when the French Sadean antiques dealer Donatien Alphonse leaves marks on her body, Paolo understands that Diana is cheating on him and throws her out of the house.",
     release_date: "1992-02-21",
     poster_path: "/h5xsSUCkXCd0696K5A9hkmgaERE.jpg",
     popularity: 5.887739,
     title: "All Ladies Do It",
     video: false,
     vote_average: 4.4,
     vote_count: 45
     */
    public boolean adult;
    public String backdrop_path;
    public ArrayList<Integer> genre_ids;
    public int id;
    public String original_language;
    public String original_title;
    public String overview;
    public String release_date;
    public String poster_path;
    public float popularity;
    public String title;
    public boolean video;
    public double vote_average;
    public int vote_count;
}
