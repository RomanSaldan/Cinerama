package com.lynx.cinerama.presentation.screens.movies;

import com.lynx.cinerama.data.model.search.ResponseMultiSearch;
import com.lynx.cinerama.data.model.search.SearchResultCommon;
import com.lynx.cinerama.data.model.search.SearchResultMovie;
import com.lynx.cinerama.data.model.search.SearchResultPerson;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.domain.SearchRepository;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MoviesPresenter implements MoviesContract.MoviesPresenter {

    private MoviesContract.MoviesView view;
    private MovieRepository movieRepository;
    private SearchRepository searchRepository;
    private int movieID;
    private CompositeSubscription compositeSubscription;


    public MoviesPresenter(MoviesContract.MoviesView view, MovieRepository movieRepository, SearchRepository searchRepository, int movieID) {
        this.view = view;
        this.movieRepository = movieRepository;
        this.searchRepository = searchRepository;
        this.movieID = movieID;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.displayProgress(true);
        compositeSubscription.add(
                movieRepository.getMovieInfo(movieID)
                        .subscribe(responseMovieInfo -> {
                            view.displayProgress(false);

                            view.setTitle(responseMovieInfo.title);
                            view.setBackdropImage(responseMovieInfo.backdrop_path);
                            view.setupMovieInfo(responseMovieInfo); //here
                            view.setupBottomBar();
                        }, t -> {
                            view.displayProgress(false);
                            view.displayError(t.getMessage());
                        })
        );
    }

    @Override
    public void unsubscribe() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void search(String query) {
        searchRepository.multiSearch(query)
                .subscribe(responseMultiSearch -> view.displaySearchResults(prepareSearchResults(responseMultiSearch)));
    }

    @Override
    public void selectSearchResult(SearchResultDH searchResultDH) {
        if(searchResultDH.getSearchResultMovie() != null) {
            view.refreshMovieInfo(searchResultDH.getSearchResultMovie().id);
        } else {
            view.startPersonScreen(searchResultDH.getSearchResultPerson().id);
        }
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
