package com.lynx.cinerama.data.model.movies.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieBelongsToCollection implements Parcelable {
    /**
     * id: 252313,
     name: "The Endless Summer Collection",
     poster_path: "/zhG3P4ALlXxoFi0CGfcVsR3qmDL.jpg",
     backdrop_path: "/rbeqwDmy0yPMxBqA2ELvu08okcn.jpg"
     */
    public int id;
    public String name;
    public String poster_path;
    public String backdrop_path;

    protected MovieBelongsToCollection(Parcel in) {
        id = in.readInt();
        name = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieBelongsToCollection> CREATOR = new Parcelable.Creator<MovieBelongsToCollection>() {
        @Override
        public MovieBelongsToCollection createFromParcel(Parcel in) {
            return new MovieBelongsToCollection(in);
        }

        @Override
        public MovieBelongsToCollection[] newArray(int size) {
            return new MovieBelongsToCollection[size];
        }
    };
}