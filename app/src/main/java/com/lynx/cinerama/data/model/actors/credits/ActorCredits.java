package com.lynx.cinerama.data.model.actors.credits;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/15/2016.
 */

public class ActorCredits implements Parcelable {
    public ArrayList<ActorCreditCast> cast;
    public ArrayList<ActorCreditCrew> crew;

    protected ActorCredits(Parcel in) {
        if (in.readByte() == 0x01) {
            cast = new ArrayList<ActorCreditCast>();
            in.readList(cast, ActorCreditCast.class.getClassLoader());
        } else {
            cast = null;
        }
        if (in.readByte() == 0x01) {
            crew = new ArrayList<ActorCreditCrew>();
            in.readList(crew, ActorCreditCrew.class.getClassLoader());
        } else {
            crew = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (cast == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(cast);
        }
        if (crew == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(crew);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ActorCredits> CREATOR = new Parcelable.Creator<ActorCredits>() {
        @Override
        public ActorCredits createFromParcel(Parcel in) {
            return new ActorCredits(in);
        }

        @Override
        public ActorCredits[] newArray(int size) {
            return new ActorCredits[size];
        }
    };
}