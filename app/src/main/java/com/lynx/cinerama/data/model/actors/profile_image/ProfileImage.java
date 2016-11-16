package com.lynx.cinerama.data.model.actors.profile_image;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by Lynx on 11/15/2016.
 */

public class ProfileImage implements Parcelable {
    /**
     * {
     aspect_ratio: 0.666666666666667,
     file_path: "/wdrSStdTmiSHhynCV5CtTJTuzY1.jpg",
     height: 1500,
     iso_639_1: null,
     vote_average: 5.43114543114543,
     vote_count: 11,
     width: 1000
     },
     */

    public float aspect_ratio;
    public String file_path;
    public int height;
    public String iso_639_1;
    public float vote_average;
    public int vote_count;
    public int width;

    public boolean getIso_639_1Valid() {
        return TextUtils.isEmpty(iso_639_1) || iso_639_1.equalsIgnoreCase("en");
    }

    protected ProfileImage(Parcel in) {
        aspect_ratio = in.readFloat();
        file_path = in.readString();
        height = in.readInt();
        iso_639_1 = in.readString();
        vote_average = in.readFloat();
        vote_count = in.readInt();
        width = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(aspect_ratio);
        dest.writeString(file_path);
        dest.writeInt(height);
        dest.writeString(iso_639_1);
        dest.writeFloat(vote_average);
        dest.writeInt(vote_count);
        dest.writeInt(width);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ProfileImage> CREATOR = new Parcelable.Creator<ProfileImage>() {
        @Override
        public ProfileImage createFromParcel(Parcel in) {
            return new ProfileImage(in);
        }

        @Override
        public ProfileImage[] newArray(int size) {
            return new ProfileImage[size];
        }
    };
}