package com.lynx.cinerama.presentation.screens.movies;

import com.lynx.cinerama.data.model.search.ResponseMultiSearch;
import com.lynx.cinerama.data.model.search.SearchResultCommon;
import com.lynx.cinerama.data.model.search.SearchResultMovie;
import com.lynx.cinerama.data.model.search.SearchResultPerson;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.domain.SearchRepository;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;
import com.lynx.cinerama.presentation.utils.Constants;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 1/3/2017.
 */

public class MoviesPresenter implements MoviesContract.MoviesPresenter {

    private MoviesContract.MoviesView view;
    private MovieRepository movieRepository;
    private SearchRepository searchRepository;
    private CompositeSubscription compositeSubscription;
    private Constants.LayoutType layoutType;

    public MoviesPresenter(MoviesContract.MoviesView view, MovieRepository movieRepository, SearchRepository searchRepository) {
        this.view = view;
        this.movieRepository = movieRepository;
        this.searchRepository = searchRepository;
        compositeSubscription = new CompositeSubscription();
        layoutType = Constants.LayoutType.LAYOUT_LINEAR;

        view.setPresenter(this);
    }

    @Override
    public void search(String query) {
        searchRepository.multiSearch(query)
                .subscribe(responseMultiSearch -> view.displaySearchResults(prepareSearchResults(responseMultiSearch)));
    }

    @Override
    public void selectSearchResult(SearchResultDH searchResultDH) {
        if(searchResultDH.getSearchResultMovie() != null) {
            view.startMovieScreen(searchResultDH.getSearchResultMovie().id);
        } else {
            view.startPersonScreen(searchResultDH.getSearchResultPerson().id);
        }
    }

    @Override
    public void changeDisplayLayout() {
        layoutType = layoutType == Constants.LayoutType.LAYOUT_LINEAR
                ? Constants.LayoutType.LAYOUT_GRID
                : Constants.LayoutType.LAYOUT_LINEAR;
        view.setLayout(layoutType);
    }

    @Override
    public void changeGenre() {

    }

    @Override
    public void getMovies(Constants.MovieFilter filter) {

    }

    @Override
    public void subscribe() {
        view.setTitle("TOP");
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    private ArrayList<SearchResultDH> prepareSearchResults(ResponseMultiSearch responseMultiSearch) {
        ArrayList<SearchResultDH> result = new ArrayList<>();
        if(responseMultiSearch != null && responseMultiSearch.total_results > 0) {
            ArrayList<SearchResultDH> foundMovies = new ArrayList<>();
            ArrayList<SearchResultDH> foundPersons = new ArrayList<>();
            boolean isFirstMovie = responseMultiSearch.results.get(0) instanceof SearchResultMovie;
            for(SearchResultCommon searchResultCommon : responseMultiSearch.results) {
                if(searchResultCommon instanceof SearchResultMovie) {
                    SearchResultMovie searchResultMovie = (SearchResultMovie) searchResultCommon;
                    foundMovies.add(new SearchResultDH(null, searchResultMovie, null));

                } else {
                    SearchResultPerson searchResultPerson = (SearchResultPerson) searchResultCommon;
                    foundPersons.add(new SearchResultDH(null, null, searchResultPerson));
                }
            }
            result.add(new SearchResultDH(isFirstMovie ? "Movies" : "Persons", null, null));
            result.addAll(isFirstMovie ? foundMovies : foundPersons);
            if((isFirstMovie && foundPersons.size() > 0) || (!isFirstMovie && foundMovies.size() >0 )) {
                result.add(new SearchResultDH(!isFirstMovie ? "Movies" : "Persons", null, null));
                result.addAll(!isFirstMovie ? foundMovies : foundPersons);
            }
        }
        return result;
    }
}
