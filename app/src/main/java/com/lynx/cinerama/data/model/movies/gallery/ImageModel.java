package com.lynx.cinerama.data.model.movies.gallery;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.lynx.cinerama.data.model.movies.similar.ShortMovieInfo;

/**
 * Created by Lynx on 10/26/2016.
 */

public class ImageModel implements Parcelable {
    /**
     * aspect_ratio: 1.77777777777778,
     file_path: "/6Ue2NvfsZemoVIw9PQx0CELzSK7.jpg",
     height: 1080,
     iso_639_1: null,
     vote_average: 0,
     vote_count: 0,
     width: 1920

     ++media type
     ++media (movie info)
     */
    public float aspect_ratio;
    public String file_path;
    public int height;
    public int width;
    public String iso_639_1;
    public float vote_average;
    public int vote_count;


    public ShortMovieInfo media;
    public String media_type;

    public boolean getIso_639_1Valid() {
        return TextUtils.isEmpty(iso_639_1) || iso_639_1.equalsIgnoreCase("en");
    }

    protected ImageModel(Parcel in) {
        aspect_ratio = in.readFloat();
        file_path = in.readString();
        height = in.readInt();
        width = in.readInt();
        iso_639_1 = in.readString();
        vote_average = in.readFloat();
        vote_count = in.readInt();
        media = (ShortMovieInfo) in.readValue(ShortMovieInfo.class.getClassLoader());
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
        dest.writeInt(width);
        dest.writeString(iso_639_1);
        dest.writeFloat(vote_average);
        dest.writeInt(vote_count);
        dest.writeValue(media);
        dest.writeString(media_type);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ImageModel> CREATOR = new Parcelable.Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };
}