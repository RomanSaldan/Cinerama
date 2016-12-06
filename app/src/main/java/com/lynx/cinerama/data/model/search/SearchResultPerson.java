package com.lynx.cinerama.data.model.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.lynx.cinerama.data.model.movies.similar.ShortMovieInfo;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/29/2016.
 */

public class SearchResultPerson extends SearchResultCommon implements Parcelable {
    /**
     *  {
     "profile_path": "/2daC5DeXqwkFND0xxutbnSVKN6c.jpg",
     "adult": false,
     "id": 51329,
     "media_type": "person",
     "known_for": [
     {
     "poster_path": "/y31QB9kn3XSudA15tV7UWQ9XLuW.jpg",
     "adult": false,
     "overview": "Light years from Earth, 26 years after being abducted, Peter Quill finds himself the prime target of a manhunt after discovering an orb wanted by Ronan the Accuser.",
     "release_date": "2014-07-30",
     "original_title": "Guardians of the Galaxy",
     "genre_ids": [
     28,
     878,
     12
     ],
     "id": 118340,
     "media_type": "movie",
     "original_language": "en",
     "title": "Guardians of the Galaxy",
     "backdrop_path": "/bHarw8xrmQeqf3t8HpuMY7zoK4x.jpg",
     "popularity": 9.267731,
     "vote_count": 5002,
     "video": false,
     "vote_average": 7.97
     },
     {
     "poster_path": "/eshEkiG7NmU4ekA8CtpIdYiYufZ.jpg",
     "adult": false,
     "overview": "When three friends finally come to after a raucous night of bachelor-party revelry, they find a baby in the closet and a tiger in the bathroom. But they can't seem to locate their best friend, Doug -- who's supposed to be tying the knot. Launching a frantic search for Doug, the trio perseveres through a nasty hangover to try to make it to the church on time.",
     "release_date": "2009-06-05",
     "original_title": "The Hangover",
     "genre_ids": [
     35
     ],
     "id": 18785,
     "media_type": "movie",
     "original_language": "en",
     "title": "The Hangover",
     "backdrop_path": "/39LohvXfll5dGCQIV9B9VJ16ImE.jpg",
     "popularity": 3.69347,
     "vote_count": 3761,
     "video": false,
     "vote_average": 7.08
     },
     {
     "poster_path": "/ilrZAV2klTB0FLxLb01bOp5pzD9.jpg",
     "adult": false,
     "overview": "After spending eight months in a mental institution, a former teacher moves back in with his parents and tries to reconcile with his ex-wife.",
     "release_date": "2012-09-08",
     "original_title": "Silver Linings Playbook",
     "genre_ids": [
     18,
     35,
     10749
     ],
     "id": 82693,
     "media_type": "movie",
     "original_language": "en",
     "title": "Silver Linings Playbook",
     "backdrop_path": "/4MKAnhMC32FIXFKSQmKkxLtHHfs.jpg",
     "popularity": 3.277653,
     "vote_count": 3074,
     "video": false,
     "vote_average": 6.9
     }
     ],
     "name": "Bradley Cooper",
     "popularity": 6.431053
     },
     */
    public String media_type;

    public String profile_path;
    public boolean adult;
    public int id;
    public String name;
    public float popularity;
    public ArrayList<ShortMovieInfo> known_for;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.media_type);
        dest.writeString(this.profile_path);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeFloat(this.popularity);
        dest.writeTypedList(this.known_for);
    }

    public SearchResultPerson() {
    }

    protected SearchResultPerson(Parcel in) {
        this.media_type = in.readString();
        this.profile_path = in.readString();
        this.adult = in.readByte() != 0;
        this.id = in.readInt();
        this.name = in.readString();
        this.popularity = in.readFloat();
        this.known_for = in.createTypedArrayList(ShortMovieInfo.CREATOR);
    }

    public static final Parcelable.Creator<SearchResultPerson> CREATOR = new Parcelable.Creator<SearchResultPerson>() {
        @Override
        public SearchResultPerson createFromParcel(Parcel source) {
            return new SearchResultPerson(source);
        }

        @Override
        public SearchResultPerson[] newArray(int size) {
            return new SearchResultPerson[size];
        }
    };
}
