package com.lynx.cinerama.data.model.actors.profile_tagged_image;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/15/2016.
 */

public class TaggedImageMediaData implements Parcelable {
    /**
     * media: {
     adult: false,
     backdrop_path: "/9MjNZ0VvscSUQ4VhwbQtbcW9jqV.jpg",
     genre_ids: [
     18,
     9648,
     53,
     80
     ],
     _id: "4bc8a8af017a3c122d045a1a",
     id: 9481,
     original_language: "en",
     original_title: "The Bone Collector",
     overview: "Rookie cop Amelia Donaghy reluctantly teams with Lincoln Rhyme -- formerly the department's top homicide detective but now paralyzed as a result of a spinal injury -- to catch a grisly serial killer dubbed The Bone Collector. The murderer's special signature is to leave tantalizing clues based on the grim remains of his crimes.",
     release_date: "1999-11-01",
     poster_path: "/dCTuPRukbDs3mOSx9SD0PCMRd2g.jpg",
     popularity: 2.492177,
     title: "The Bone Collector",
     video: false,
     vote_average: 6.5,
     vote_count: 428
     },
     */

    public boolean adult;
    public String backdrop_path;
    public ArrayList<Integer> genre_ids;
    public String _id;
    public int id;
    public String original_language;
    public String original_title;
    public String overview;
    public String release_date;
    public String poster_path;
    public float popularity;
    public String title;
    public boolean video;
    public double vote_average;
    public int vote_count;

    protected TaggedImageMediaData(Parcel in) {
        adult = in.readByte() != 0x00;
        backdrop_path = in.readString();
        if (in.readByte() == 0x01) {
            genre_ids = new ArrayList<Integer>();
            in.readList(genre_ids, Integer.class.getClassLoader());
        } else {
            genre_ids = null;
        }
        _id = in.readString();
        id = in.readInt();
        original_language = in.readString();
        original_title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        popularity = in.readFloat();
        title = in.readString();
        video = in.readByte() != 0x00;
        vote_average = in.readDouble();
        vote_count = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (adult ? 0x01 : 0x00));
        dest.writeString(backdrop_path);
        if (genre_ids == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(genre_ids);
        }
        dest.writeString(_id);
        dest.writeInt(id);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(poster_path);
        dest.writeFloat(popularity);
        dest.writeString(title);
        dest.writeByte((byte) (video ? 0x01 : 0x00));
        dest.writeDouble(vote_average);
        dest.writeInt(vote_count);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TaggedImageMediaData> CREATOR = new Parcelable.Creator<TaggedImageMediaData>() {
        @Override
        public TaggedImageMediaData createFromParcel(Parcel in) {
            return new TaggedImageMediaData(in);
        }

        @Override
        public TaggedImageMediaData[] newArray(int size) {
            return new TaggedImageMediaData[size];
        }
    };
}