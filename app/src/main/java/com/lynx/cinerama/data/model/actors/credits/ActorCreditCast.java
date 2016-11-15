package com.lynx.cinerama.data.model.actors.credits;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 11/15/2016.
 */

public class ActorCreditCast implements Parcelable {
    /**
     * {
     adult: false,
     character: "Angela",
     credit_id: "55f4402cc3a3686d27002e8a",
     id: 359468,
     original_title: "Angela & Viril",
     poster_path: "/wHz3aibdbv5xhDF8SkE9Q1JaaJd.jpg",
     release_date: "1993-01-01",
     title: "Angela & Viril"
     }
     */

    public boolean adult;
    public String character;
    public String credit_id;
    public int id;
    public String original_title;
    public String poster_path;
    public String release_date;
    public String title;

    protected ActorCreditCast(Parcel in) {
        adult = in.readByte() != 0x00;
        character = in.readString();
        credit_id = in.readString();
        id = in.readInt();
        original_title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (adult ? 0x01 : 0x00));
        dest.writeString(character);
        dest.writeString(credit_id);
        dest.writeInt(id);
        dest.writeString(original_title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(title);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ActorCreditCast> CREATOR = new Parcelable.Creator<ActorCreditCast>() {
        @Override
        public ActorCreditCast createFromParcel(Parcel in) {
            return new ActorCreditCast(in);
        }

        @Override
        public ActorCreditCast[] newArray(int size) {
            return new ActorCreditCast[size];
        }
    };
}