package com.lynx.cinerama.data.model.actors.profile_image;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/15/2016.
 */

public class ActorProfiles implements Parcelable {
    public ArrayList<ProfileImage> profiles;

    protected ActorProfiles(Parcel in) {
        if (in.readByte() == 0x01) {
            profiles = new ArrayList<ProfileImage>();
            in.readList(profiles, ProfileImage.class.getClassLoader());
        } else {
            profiles = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (profiles == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(profiles);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ActorProfiles> CREATOR = new Parcelable.Creator<ActorProfiles>() {
        @Override
        public ActorProfiles createFromParcel(Parcel in) {
            return new ActorProfiles(in);
        }

        @Override
        public ActorProfiles[] newArray(int size) {
            return new ActorProfiles[size];
        }
    };
}