package com.lynx.cinerama.data.model.movies.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieSpokenLanguage implements Parcelable {
    /**
     * "iso_639_1": "en",
     "name": "English"
     */
    public String iso_639_1;
    public String name;

    protected MovieSpokenLanguage(Parcel in) {
        iso_639_1 = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iso_639_1);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieSpokenLanguage> CREATOR = new Parcelable.Creator<MovieSpokenLanguage>() {
        @Override
        public MovieSpokenLanguage createFromParcel(Parcel in) {
            return new MovieSpokenLanguage(in);
        }

        @Override
        public MovieSpokenLanguage[] newArray(int size) {
            return new MovieSpokenLanguage[size];
        }
    };
}