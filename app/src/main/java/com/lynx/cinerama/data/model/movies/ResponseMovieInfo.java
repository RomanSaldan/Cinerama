package com.lynx.cinerama.data.model.movies;

import android.os.Parcel;
import android.os.Parcelable;

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

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class ResponseMovieInfo implements Parcelable {
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

    protected ResponseMovieInfo(Parcel in) {
        adult = in.readByte() != 0x00;
        backdrop_path = in.readString();
        belongs_to_collection = (MovieBelongsToCollection) in.readValue(MovieBelongsToCollection.class.getClassLoader());
        budget = in.readLong();
        if (in.readByte() == 0x01) {
            genres = new ArrayList<MovieGenre>();
            in.readList(genres, MovieGenre.class.getClassLoader());
        } else {
            genres = null;
        }
        homepage = in.readString();
        id = in.readInt();
        imdb_id = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        overview = in.readString();
        popularity = in.readFloat();
        poster_path = in.readString();
        if (in.readByte() == 0x01) {
            production_companies = new ArrayList<MovieProductionCompany>();
            in.readList(production_companies, MovieProductionCompany.class.getClassLoader());
        } else {
            production_companies = null;
        }
        if (in.readByte() == 0x01) {
            production_countries = new ArrayList<MovieProductionCountry>();
            in.readList(production_countries, MovieProductionCountry.class.getClassLoader());
        } else {
            production_countries = null;
        }
        release_date = in.readString();
        revenue = in.readLong();
        runtime = in.readInt();
        if (in.readByte() == 0x01) {
            spoken_languages = new ArrayList<MovieSpokenLanguage>();
            in.readList(spoken_languages, MovieSpokenLanguage.class.getClassLoader());
        } else {
            spoken_languages = null;
        }
        status = in.readString();
        tagline = in.readString();
        title = in.readString();
        video = in.readByte() != 0x00;
        vote_average = in.readDouble();
        vote_count = in.readInt();
        similar = (MovieSimilar) in.readValue(MovieSimilar.class.getClassLoader());
        reviews = (MovieReviews) in.readValue(MovieReviews.class.getClassLoader());
        images = (MovieGallery) in.readValue(MovieGallery.class.getClassLoader());
        credits = (MovieCredits) in.readValue(MovieCredits.class.getClassLoader());
        videos = (MovieVideos) in.readValue(MovieVideos.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (adult ? 0x01 : 0x00));
        dest.writeString(backdrop_path);
        dest.writeValue(belongs_to_collection);
        dest.writeLong(budget);
        if (genres == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(genres);
        }
        dest.writeString(homepage);
        dest.writeInt(id);
        dest.writeString(imdb_id);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeFloat(popularity);
        dest.writeString(poster_path);
        if (production_companies == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(production_companies);
        }
        if (production_countries == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(production_countries);
        }
        dest.writeString(release_date);
        dest.writeLong(revenue);
        dest.writeInt(runtime);
        if (spoken_languages == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(spoken_languages);
        }
        dest.writeString(status);
        dest.writeString(tagline);
        dest.writeString(title);
        dest.writeByte((byte) (video ? 0x01 : 0x00));
        dest.writeDouble(vote_average);
        dest.writeInt(vote_count);
        dest.writeValue(similar);
        dest.writeValue(reviews);
        dest.writeValue(images);
        dest.writeValue(credits);
        dest.writeValue(videos);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ResponseMovieInfo> CREATOR = new Parcelable.Creator<ResponseMovieInfo>() {
        @Override
        public ResponseMovieInfo createFromParcel(Parcel in) {
            return new ResponseMovieInfo(in);
        }

        @Override
        public ResponseMovieInfo[] newArray(int size) {
            return new ResponseMovieInfo[size];
        }
    };
}