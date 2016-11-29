package com.lynx.cinerama.domain;

import com.lynx.cinerama.data.api.Rest;
import com.lynx.cinerama.data.model.search.ResponseMultiSearch;
import com.lynx.cinerama.data.services.SearchService;

import org.androidannotations.annotations.EBean;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lynx on 11/29/2016.
 */

@EBean(scope = EBean.Scope.Singleton)
public class SearchRepository {

    private SearchService searchService;

    public SearchRepository() {
        searchService = Rest.getInstance().getSearchService();
    }

    private <T> Observable<T> getNetworkObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public Observable<ResponseMultiSearch> multiSearch(String query) {
        return getNetworkObservable(searchService.multiSearch(query));
    }
}
