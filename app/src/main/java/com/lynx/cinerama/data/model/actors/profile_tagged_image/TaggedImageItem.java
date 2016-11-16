package com.lynx.cinerama.data.model.actors.profile_tagged_image;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by Lynx on 11/15/2016.
 */

public class TaggedImageItem implements Parcelable {
    /**
     * {
     aspect_ratio: 0.666666666666667,
     file_path: "/dCTuPRukbDs3mOSx9SD0PCMRd2g.jpg",
     height: 1500,
     id: "4ea6090b9dc3d83c3b002c33",
     iso_639_1: "en",
     vote_average: 5.81587301587302,
     vote_count: 12,
     width: 1000,
     image_type: "poster",
     media: {
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
     media_type: "movie"
     },
     */

    public float aspect_ratio;
    public String file_path;
    public int height;
    public String id;
    public String iso_639_1;
    public float vote_average;
    public int vote_count;
    public int width;
    public String image_type;
    public TaggedImageMediaData media;
    public String media_type;

    public boolean getIso_639_1Valid() {
        return TextUtils.isEmpty(iso_639_1) || iso_639_1.equalsIgnoreCase("en");
    }

    public boolean isMediaTypeMovie() {
        return TextUtils.isEmpty(iso_639_1) || iso_639_1.equalsIgnoreCase("movie");
    }

    protected TaggedImageItem(Parcel in) {
        aspect_ratio = in.readFloat();
        file_path = in.readString();
        height = in.readInt();
        id = in.readString();
        iso_639_1 = in.readString();
        vote_average = in.readFloat();
        vote_count = in.readInt();
        width = in.readInt();
        image_type = in.readString();
        media = (TaggedImageMediaData) in.readValue(TaggedImageMediaData.class.getClassLoader());
        media_type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(aspect_ratio);
        dest.writeString(file_path);
        dest.writeInt(height);
        dest.writeString(id);
        dest.writeString(iso_639_1);
        dest.writeFloat(vote_average);
        dest.writeInt(vote_count);
        dest.writeInt(width);
        dest.writeString(image_type);
        dest.writeValue(media);
        dest.writeString(media_type);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TaggedImageItem> CREATOR = new Parcelable.Creator<TaggedImageItem>() {
        @Override
        public TaggedImageItem createFromParcel(Parcel in) {
            return new TaggedImageItem(in);
        }

        @Override
        public TaggedImageItem[] newArray(int size) {
            return new TaggedImageItem[size];
        }
    };
}