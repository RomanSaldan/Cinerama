package com.lynx.cinerama.data.model.movies.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieProductionCountry implements Parcelable {
    /**
     "iso_3166_1": "DE",
     "name": "Germany"
     */
    public String iso_3166_1;
    public String name;

    protected MovieProductionCountry(Parcel in) {
        iso_3166_1 = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iso_3166_1);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieProductionCountry> CREATOR = new Parcelable.Creator<MovieProductionCountry>() {
        @Override
        public MovieProductionCountry createFromParcel(Parcel in) {
            return new MovieProductionCountry(in);
        }

        @Override
        public MovieProductionCountry[] newArray(int size) {
            return new MovieProductionCountry[size];
        }
    };
}