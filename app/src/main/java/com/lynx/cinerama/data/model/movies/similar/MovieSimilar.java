package com.lynx.cinerama.data.model.movies.similar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieSimilar implements Parcelable {
    public int page;
    public int total_pages;
    public int total_results;
    public ArrayList<ShortMovieInfo> results;

    protected MovieSimilar(Parcel in) {
        page = in.readInt();
        total_pages = in.readInt();
        total_results = in.readInt();
        if (in.readByte() == 0x01) {
            results = new ArrayList<ShortMovieInfo>();
            in.readList(results, ShortMovieInfo.class.getClassLoader());
        } else {
            results = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(total_pages);
        dest.writeInt(total_results);
        if (results == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(results);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieSimilar> CREATOR = new Parcelable.Creator<MovieSimilar>() {
        @Override
        public MovieSimilar createFromParcel(Parcel in) {
            return new MovieSimilar(in);
        }

        @Override
        public MovieSimilar[] newArray(int size) {
            return new MovieSimilar[size];
        }
    };
}