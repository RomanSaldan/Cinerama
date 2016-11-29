package com.lynx.cinerama.data.services;

import com.lynx.cinerama.data.model.search.ResponseMultiSearch;
import com.lynx.cinerama.presentation.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lynx on 11/29/2016.
 */

public interface SearchService {

    @GET(Constants.GET_MULTI_SEARCH)
    Observable<ResponseMultiSearch> multiSearch(@Query("query") String query);

}
