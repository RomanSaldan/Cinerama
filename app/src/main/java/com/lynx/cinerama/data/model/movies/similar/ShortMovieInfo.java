package com.lynx.cinerama.data.model.movies.similar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class ShortMovieInfo implements Parcelable {
    /**
     * adult: false,
     backdrop_path: "/uLLcFSd7fpea7wtSppUClq6cmpe.jpg",
     genre_ids: [
     35,
     18
     ],
     id: 37265,
     original_language: "it",
     original_title: "Così fan tutte",
     overview: "Diana is a Roman wife happily married to sympathetic Paolo but she is keen on playing benign games of seduction with other men while resisting the advances of chic lingerie shop owner Silvio and she narrates her adventures to Paolo in order to stimulate their otherwise monotonous sexual life. However, under the influence of her lesbian friend Antonietta and raunchy sister Nadia, Diana starts to move the ongoings further while Paolo is still prone to believing that events narrated by her are merely fantasies. Nevertheless, when the French Sadean antiques dealer Donatien Alphonse leaves marks on her body, Paolo understands that Diana is cheating on him and throws her out of the house.",
     release_date: "1992-02-21",
     poster_path: "/h5xsSUCkXCd0696K5A9hkmgaERE.jpg",
     popularity: 5.887739,
     title: "All Ladies Do It",
     video: false,
     vote_average: 4.4,
     vote_count: 45
     */
    public boolean adult;
    public String backdrop_path;
    public ArrayList<Integer> genre_ids;
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

    protected ShortMovieInfo(Parcel in) {
        adult = in.readByte() != 0x00;
        backdrop_path = in.readString();
        if (in.readByte() == 0x01) {
            genre_ids = new ArrayList<Integer>();
            in.readList(genre_ids, Integer.class.getClassLoader());
        } else {
            genre_ids = null;
        }
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
    public static final Parcelable.Creator<ShortMovieInfo> CREATOR = new Parcelable.Creator<ShortMovieInfo>() {
        @Override
        public ShortMovieInfo createFromParcel(Parcel in) {
            return new ShortMovieInfo(in);
        }

        @Override
        public ShortMovieInfo[] newArray(int size) {
            return new ShortMovieInfo[size];
        }
    };
}