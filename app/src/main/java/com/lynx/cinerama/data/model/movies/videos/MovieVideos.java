package com.lynx.cinerama.data.model.movies.videos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieVideos implements Parcelable {
    public ArrayList<MovieVideo> results;

    protected MovieVideos(Parcel in) {
        if (in.readByte() == 0x01) {
            results = new ArrayList<MovieVideo>();
            in.readList(results, MovieVideo.class.getClassLoader());
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
        if (results == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(results);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieVideos> CREATOR = new Parcelable.Creator<MovieVideos>() {
        @Override
        public MovieVideos createFromParcel(Parcel in) {
            return new MovieVideos(in);
        }

        @Override
        public MovieVideos[] newArray(int size) {
            return new MovieVideos[size];
        }
    };
}