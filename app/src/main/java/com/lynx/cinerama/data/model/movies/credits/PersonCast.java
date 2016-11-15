package com.lynx.cinerama.data.model.movies.credits;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 10/26/2016.
 */

public class PersonCast implements Parcelable {
    /**
     * cast_id: 406,
     character: "Conference Attendee (uncredited)",
     credit_id: "56db0d319251417a4e002490",
     id: 1586759,
     name: "Francis Brooke",
     order: 139,
     profile_path: "/b0pGKoaPbL3tW6cGZHFTvlG0bbw.jpg"
     */
    public int cast_id;
    public String character;
    public String credit_id;
    public int id;
    public String name;
    public int order;
    public String profile_path;

    protected PersonCast(Parcel in) {
        cast_id = in.readInt();
        character = in.readString();
        credit_id = in.readString();
        id = in.readInt();
        name = in.readString();
        order = in.readInt();
        profile_path = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cast_id);
        dest.writeString(character);
        dest.writeString(credit_id);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(order);
        dest.writeString(profile_path);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PersonCast> CREATOR = new Parcelable.Creator<PersonCast>() {
        @Override
        public PersonCast createFromParcel(Parcel in) {
            return new PersonCast(in);
        }

        @Override
        public PersonCast[] newArray(int size) {
            return new PersonCast[size];
        }
    };
}