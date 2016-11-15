package com.lynx.cinerama.data.model.movies.gallery;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieGallery implements Parcelable {
    public int id;
    public ArrayList<ImageModel> backdrops;
    public ArrayList<ImageModel> posters;

    protected MovieGallery(Parcel in) {
        id = in.readInt();
        if (in.readByte() == 0x01) {
            backdrops = new ArrayList<ImageModel>();
            in.readList(backdrops, ImageModel.class.getClassLoader());
        } else {
            backdrops = null;
        }
        if (in.readByte() == 0x01) {
            posters = new ArrayList<ImageModel>();
            in.readList(posters, ImageModel.class.getClassLoader());
        } else {
            posters = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        if (backdrops == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(backdrops);
        }
        if (posters == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(posters);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieGallery> CREATOR = new Parcelable.Creator<MovieGallery>() {
        @Override
        public MovieGallery createFromParcel(Parcel in) {
            return new MovieGallery(in);
        }

        @Override
        public MovieGallery[] newArray(int size) {
            return new MovieGallery[size];
        }
    };
}