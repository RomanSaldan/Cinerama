package com.lynx.cinerama.data.model.search;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lynx on 11/29/2016.
 */

public class SearchResultCommon implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public SearchResultCommon() {
    }

    protected SearchResultCommon(Parcel in) {
    }

    public static final Parcelable.Creator<SearchResultCommon> CREATOR = new Parcelable.Creator<SearchResultCommon>() {
        @Override
        public SearchResultCommon createFromParcel(Parcel source) {
            return new SearchResultCommon(source);
        }

        @Override
        public SearchResultCommon[] newArray(int size) {
            return new SearchResultCommon[size];
        }
    };
}
