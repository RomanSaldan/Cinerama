package com.lynx.cinerama.data.model.actors;

import android.os.Parcel;
import android.os.Parcelable;

import com.lynx.cinerama.data.model.actors.credits.ActorCredits;
import com.lynx.cinerama.data.model.actors.profile_image.ActorProfiles;
import com.lynx.cinerama.data.model.actors.profile_tagged_image.ActorTaggedImages;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/15/2016.
 */

public class ResponseActorInfo implements Parcelable {
    public boolean adult;
    public ArrayList<String> also_known_as;
    public String biography;
    public String birthday;
    public String deathday;
    public int gender;
    public String homepage;
    public int id;
    public String imdb_id;
    public String name;
    public String place_of_birth;
    public float popularity;
    public String profile_path;

    public ActorCredits credits;
    public ActorProfiles images;
    public ActorTaggedImages tagged_images;

    protected ResponseActorInfo(Parcel in) {
        adult = in.readByte() != 0x00;
        if (in.readByte() == 0x01) {
            also_known_as = new ArrayList<String>();
            in.readList(also_known_as, String.class.getClassLoader());
        } else {
            also_known_as = null;
        }
        biography = in.readString();
        birthday = in.readString();
        deathday = in.readString();
        gender = in.readInt();
        homepage = in.readString();
        id = in.readInt();
        imdb_id = in.readString();
        name = in.readString();
        place_of_birth = in.readString();
        popularity = in.readFloat();
        profile_path = in.readString();
        credits = (ActorCredits) in.readValue(ActorCredits.class.getClassLoader());
        images = (ActorProfiles) in.readValue(ActorProfiles.class.getClassLoader());
        tagged_images = (ActorTaggedImages) in.readValue(ActorTaggedImages.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (adult ? 0x01 : 0x00));
        if (also_known_as == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(also_known_as);
        }
        dest.writeString(biography);
        dest.writeString(birthday);
        dest.writeString(deathday);
        dest.writeInt(gender);
        dest.writeString(homepage);
        dest.writeInt(id);
        dest.writeString(imdb_id);
        dest.writeString(name);
        dest.writeString(place_of_birth);
        dest.writeFloat(popularity);
        dest.writeString(profile_path);
        dest.writeValue(credits);
        dest.writeValue(images);
        dest.writeValue(tagged_images);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ResponseActorInfo> CREATOR = new Parcelable.Creator<ResponseActorInfo>() {
        @Override
        public ResponseActorInfo createFromParcel(Parcel in) {
            return new ResponseActorInfo(in);
        }

        @Override
        public ResponseActorInfo[] newArray(int size) {
            return new ResponseActorInfo[size];
        }
    };
}