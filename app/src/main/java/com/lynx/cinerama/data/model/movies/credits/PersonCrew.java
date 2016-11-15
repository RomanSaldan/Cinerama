package com.lynx.cinerama.data.model.movies.credits;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 10/26/2016.
 */

public class PersonCrew implements Parcelable {
    /**
     * credit_id: "52fe4a6dc3a36847f81cd4bf",
     department: "Directing",
     id: 1032,
     job: "Director",
     name: "Martin Scorsese",
     profile_path: "/yGs4PPDPTJV687P1NdydeEFzYRK.jpg"
     */
    public String credit_id;
    public String department;
    public int id;
    public String job;
    public String name;
    public String profile_path;

    protected PersonCrew(Parcel in) {
        credit_id = in.readString();
        department = in.readString();
        id = in.readInt();
        job = in.readString();
        name = in.readString();
        profile_path = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(credit_id);
        dest.writeString(department);
        dest.writeInt(id);
        dest.writeString(job);
        dest.writeString(name);
        dest.writeString(profile_path);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PersonCrew> CREATOR = new Parcelable.Creator<PersonCrew>() {
        @Override
        public PersonCrew createFromParcel(Parcel in) {
            return new PersonCrew(in);
        }

        @Override
        public PersonCrew[] newArray(int size) {
            return new PersonCrew[size];
        }
    };
}