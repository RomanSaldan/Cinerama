package com.lynx.cinerama.data.model.movies.videos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieVideo implements Parcelable {
    /**
     * id: "533ec6e0c3a3685448008607",
     iso_639_1: "en",
     iso_3166_1: "US",
     key: "iszwuX1AK6A",
     name: "Official Trailer",
     site: "YouTube",
     size: 720,
     type: "Trailer"
     */
    public String id;
    public String iso_639_1;
    public String iso_3166_1;
    public String key;
    public String name;
    public String site;
    public int size;
    public String type;

    protected MovieVideo(Parcel in) {
        id = in.readString();
        iso_639_1 = in.readString();
        iso_3166_1 = in.readString();
        key = in.readString();
        name = in.readString();
        site = in.readString();
        size = in.readInt();
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(iso_639_1);
        dest.writeString(iso_3166_1);
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(site);
        dest.writeInt(size);
        dest.writeString(type);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieVideo> CREATOR = new Parcelable.Creator<MovieVideo>() {
        @Override
        public MovieVideo createFromParcel(Parcel in) {
            return new MovieVideo(in);
        }

        @Override
        public MovieVideo[] newArray(int size) {
            return new MovieVideo[size];
        }
    };
}