package com.lynx.cinerama.data.model.movies;

import com.lynx.cinerama.data.model.movies.credits.MovieCredits;
import com.lynx.cinerama.data.model.movies.gallery.MovieGallery;
import com.lynx.cinerama.data.model.movies.info.MovieBelongsToCollection;
import com.lynx.cinerama.data.model.movies.info.MovieGenre;
import com.lynx.cinerama.data.model.movies.info.MovieProductionCompany;
import com.lynx.cinerama.data.model.movies.info.MovieProductionCountry;
import com.lynx.cinerama.data.model.movies.info.MovieSpokenLanguage;
import com.lynx.cinerama.data.model.movies.reviews.MovieReviews;
import com.lynx.cinerama.data.model.movies.similar.MovieSimilar;
import com.lynx.cinerama.data.model.movies.videos.MovieVideos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class ResponseMovieInfo implements Serializable {
    /**
     * {
     "adult": false,
     "backdrop_path": "/hNFMawyNDWZKKHU4GYCBz1krsRM.jpg",
     "belongs_to_collection": null,
     "budget": 63000000,
     "genres": [
     {
     "id": 18,
     "name": "Drama"
     }
     ],
     "homepage": "",
     "id": 550,
     "imdb_id": "tt0137523",
     "original_language": "en",
     "original_title": "Fight Club",
     "overview": "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
     "popularity": 2.50307202280779,
     "poster_path": "/2lECpi35Hnbpa4y46JX0aY3AWTy.jpg",
     "production_companies": [
     {
     "name": "20th Century Fox",
     "id": 25
     },
     {
     "name": "Fox 2000 Pictures",
     "id": 711
     },
     {
     "name": "Regency Enterprises",
     "id": 508
     }
     ],
     "production_countries": [
     {
     "iso_3166_1": "DE",
     "name": "Germany"
     },
     {
     "iso_3166_1": "US",
     "name": "United States of America"
     }
     ],
     "release_date": "1999-10-14",
     "revenue": 100853753,
     "runtime": 139,
     "spoken_languages": [
     {
     "iso_639_1": "en",
     "name": "English"
     }
     ],
     "status": "Released",
     "tagline": "How much can you know about yourself if you've never been in a fight?",
     "title": "Fight Club",
     "video": false,
     "vote_average": 7.7,
     "vote_count": 3185
     }
     */
    public boolean adult;
    public String backdrop_path;
    public MovieBelongsToCollection belongs_to_collection;
    public long budget;
    public ArrayList<MovieGenre> genres;
    public String homepage;
    public int id;
    public String imdb_id;
    public String original_language;
    public String original_title;
    public String overview;
    public float popularity;
    public String poster_path;
    public ArrayList<MovieProductionCompany> production_companies;
    public ArrayList<MovieProductionCountry> production_countries;
    public String release_date;
    public long revenue;
    public int runtime;
    public ArrayList<MovieSpokenLanguage> spoken_languages;
    public String status;
    public String tagline;
    public String title;
    public boolean video;
    public double vote_average;
    public int vote_count;
    //appended
    public MovieSimilar similar;
    public MovieReviews reviews;
    public MovieGallery images;
    public MovieCredits credits;
    public MovieVideos videos;
}
