package com.lynx.cinerama.data.model.search;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 11/29/2016.
 */

public class SearchResultMovie extends SearchResultCommon implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.media_type);
        dest.writeString(this.poster_path);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.original_title);
        dest.writeIntArray(this.genre_ids);
        dest.writeInt(this.id);
        dest.writeString(this.original_language);
        dest.writeString(this.title);
        dest.writeString(this.backdrop_path);
        dest.writeFloat(this.popularity);
        dest.writeInt(this.vote_count);
        dest.writeInt(this.vote_average);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
    }

    public SearchResultMovie() {
    }

    protected SearchResultMovie(Parcel in) {
        this.media_type = in.readString();
        this.poster_path = in.readString();
        this.adult = in.readByte() != 0;
        this.overview = in.readString();
        this.release_date = in.readString();
        this.original_title = in.readString();
        this.genre_ids = in.createIntArray();
        this.id = in.readInt();
        this.original_language = in.readString();
        this.title = in.readString();
        this.backdrop_path = in.readString();
        this.popularity = in.readFloat();
        this.vote_count = in.readInt();
        this.vote_average = in.readInt();
        this.video = in.readByte() != 0;
    }

    public static final Parcelable.Creator<SearchResultMovie> CREATOR = new Parcelable.Creator<SearchResultMovie>() {
        @Override
        public SearchResultMovie createFromParcel(Parcel source) {
            return new SearchResultMovie(source);
        }

        @Override
        public SearchResultMovie[] newArray(int size) {
            return new SearchResultMovie[size];
        }
    };
}
