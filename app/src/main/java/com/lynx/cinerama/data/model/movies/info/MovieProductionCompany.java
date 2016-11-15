package com.lynx.cinerama.data.model.movies.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieProductionCompany implements Parcelable {
    /**
     *  "name": "20th Century Fox",
     "id": 25
     */
    public String name;
    public int id;

    protected MovieProductionCompany(Parcel in) {
        name = in.readString();
        id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieProductionCompany> CREATOR = new Parcelable.Creator<MovieProductionCompany>() {
        @Override
        public MovieProductionCompany createFromParcel(Parcel in) {
            return new MovieProductionCompany(in);
        }

        @Override
        public MovieProductionCompany[] newArray(int size) {
            return new MovieProductionCompany[size];
        }
    };
}