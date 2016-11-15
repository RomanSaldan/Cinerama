package com.lynx.cinerama.data.model.actors.credits;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 11/15/2016.
 */

public class ActorCreditCrew implements Parcelable {
    /**
     * adult: false,
     credit_id: "52fe49e4c3a368484e145e8f",
     department: "Directing",
     id: 79777,
     job: "Director",
     original_title: "In the Land of Blood and Honey",
     poster_path: "/qNtDjUvJ7G6naeGvAmkBKUx1duD.jpg",
     release_date: "2011-12-23",
     title: "In the Land of Blood and Honey"
     */

    public boolean adult;
    public String credit_id;
    public String department;
    public int id;
    public String job;
    public String original_title;
    public String poster_path;
    public String release_date;
    public String title;

    protected ActorCreditCrew(Parcel in) {
        adult = in.readByte() != 0x00;
        credit_id = in.readString();
        department = in.readString();
        id = in.readInt();
        job = in.readString();
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
        dest.writeString(credit_id);
        dest.writeString(department);
        dest.writeInt(id);
        dest.writeString(job);
        dest.writeString(original_title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(title);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ActorCreditCrew> CREATOR = new Parcelable.Creator<ActorCreditCrew>() {
        @Override
        public ActorCreditCrew createFromParcel(Parcel in) {
            return new ActorCreditCrew(in);
        }

        @Override
        public ActorCreditCrew[] newArray(int size) {
            return new ActorCreditCrew[size];
        }
    };
}