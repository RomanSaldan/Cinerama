package com.lynx.cinerama.data.model.movies.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieGenre implements Parcelable {
    /**
     "id": 18,
     "name": "Drama"
     */
    public int id;
    public String name;

    protected MovieGenre(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieGenre> CREATOR = new Parcelable.Creator<MovieGenre>() {
        @Override
        public MovieGenre createFromParcel(Parcel in) {
            return new MovieGenre(in);
        }

        @Override
        public MovieGenre[] newArray(int size) {
            return new MovieGenre[size];
        }
    };
}