package com.lynx.cinerama.data.model.movies.credits;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MovieCredits implements Parcelable {
    public ArrayList<PersonCast> cast;
    public ArrayList<PersonCrew> crew;

    protected MovieCredits(Parcel in) {
        if (in.readByte() == 0x01) {
            cast = new ArrayList<PersonCast>();
            in.readList(cast, PersonCast.class.getClassLoader());
        } else {
            cast = null;
        }
        if (in.readByte() == 0x01) {
            crew = new ArrayList<PersonCrew>();
            in.readList(crew, PersonCrew.class.getClassLoader());
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
    public static final Parcelable.Creator<MovieCredits> CREATOR = new Parcelable.Creator<MovieCredits>() {
        @Override
        public MovieCredits createFromParcel(Parcel in) {
            return new MovieCredits(in);
        }

        @Override
        public MovieCredits[] newArray(int size) {
            return new MovieCredits[size];
        }
    };
}