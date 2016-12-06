package com.lynx.cinerama.presentation.holders.data;

import com.lynx.cinerama.data.model.search.SearchResultMovie;
import com.lynx.cinerama.data.model.search.SearchResultPerson;
import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by Lynx on 12/2/2016.
 */

public class SearchResultDH extends RecyclerDH {

    private String title;
    private SearchResultMovie searchResultMovie;
    private SearchResultPerson searchResultPerson;

    public SearchResultDH(String title, SearchResultMovie searchResultMovie, SearchResultPerson searchResultPerson) {
        this.title = title;
        this.searchResultMovie = searchResultMovie;
        this.searchResultPerson = searchResultPerson;
    }

    public String getTitle() {
        return title;
    }

    public SearchResultMovie getSearchResultMovie() {
        return searchResultMovie;
    }

    public SearchResultPerson getSearchResultPerson() {
        return searchResultPerson;
    }
}
