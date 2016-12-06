package com.lynx.cinerama.data.model.search;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/29/2016.
 */

public class ResponseMultiSearch implements Parcelable {
    public int page;
    public int total_pages;
    public int total_results;
    public ArrayList<SearchResultCommon> results;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.total_pages);
        dest.writeInt(this.total_results);
        dest.writeTypedList(this.results);
    }

    public ResponseMultiSearch() {
    }

    protected ResponseMultiSearch(Parcel in) {
        this.page = in.readInt();
        this.total_pages = in.readInt();
        this.total_results = in.readInt();
        this.results = in.createTypedArrayList(SearchResultCommon.CREATOR);
    }

    public static final Parcelable.Creator<ResponseMultiSearch> CREATOR = new Parcelable.Creator<ResponseMultiSearch>() {
        @Override
        public ResponseMultiSearch createFromParcel(Parcel source) {
            return new ResponseMultiSearch(source);
        }

        @Override
        public ResponseMultiSearch[] newArray(int size) {
            return new ResponseMultiSearch[size];
        }
    };
}
