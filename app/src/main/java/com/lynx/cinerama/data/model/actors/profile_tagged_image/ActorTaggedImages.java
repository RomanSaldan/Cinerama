package com.lynx.cinerama.data.model.actors.profile_tagged_image;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/15/2016.
 */

public class ActorTaggedImages implements Parcelable {
    public int page;
    public ArrayList<TaggedImageItem> results;
    public int total_pages;
    public int total_results;

    protected ActorTaggedImages(Parcel in) {
        page = in.readInt();
        if (in.readByte() == 0x01) {
            results = new ArrayList<TaggedImageItem>();
            in.readList(results, TaggedImageItem.class.getClassLoader());
        } else {
            results = null;
        }
        total_pages = in.readInt();
        total_results = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        if (results == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(results);
        }
        dest.writeInt(total_pages);
        dest.writeInt(total_results);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ActorTaggedImages> CREATOR = new Parcelable.Creator<ActorTaggedImages>() {
        @Override
        public ActorTaggedImages createFromParcel(Parcel in) {
            return new ActorTaggedImages(in);
        }

        @Override
        public ActorTaggedImages[] newArray(int size) {
            return new ActorTaggedImages[size];
        }
    };
}