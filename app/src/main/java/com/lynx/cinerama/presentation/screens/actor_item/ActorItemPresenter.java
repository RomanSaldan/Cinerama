package com.lynx.cinerama.presentation.screens.actor_item;

import com.lynx.cinerama.data.model.search.ResponseMultiSearch;
import com.lynx.cinerama.data.model.search.SearchResultCommon;
import com.lynx.cinerama.data.model.search.SearchResultMovie;
import com.lynx.cinerama.data.model.search.SearchResultPerson;
import com.lynx.cinerama.domain.ActorRepository;
import com.lynx.cinerama.domain.SearchRepository;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lynx on 11/15/2016.
 */

public class ActorItemPresenter implements ActorItemContract.ActorsPresenter {

    private ActorItemContract.ActorsView view;
    private ActorRepository actorRepository;
    private SearchRepository searchRepository;
    private int actorID;
    private CompositeSubscription compositeSubscription;

    public ActorItemPresenter(ActorItemContract.ActorsView view, ActorRepository actorRepository, SearchRepository searchRepository, int actorID) {
        this.view = view;
        this.actorRepository = actorRepository;
        this.searchRepository = searchRepository;
        this.actorID = actorID;
        compositeSubscription = new CompositeSubscription();

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.displayProgress(true);
        compositeSubscription.add(
                actorRepository.getActorInfo(actorID)
                .subscribe(responseActorInfo -> {
                    view.displayProgress(false);
                    view.displayActorName(responseActorInfo.name);
                    view.displayActorImage(responseActorInfo.profile_path);
                    view.setupActorInfo(responseActorInfo);
                    view.setupBottomBar();
                }, t -> {
                    view.displayProgress(false);
                    view.displayError(t.getMessage());
                })
        );
    }

    @Override
    public void search(String query) {
        searchRepository.multiSearch(query)
                .subscribe(responseMultiSearch -> view.displaySearchResults(prepareSearchResults(responseMultiSearch)));
    }

    @Override
    public void unsubscribe() {
        if(compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void selectSearchResult(SearchResultDH searchResultDH) {
        if(searchResultDH.getSearchResultMovie() != null) {
            view.startMovieScreen(searchResultDH.getSearchResultMovie().id);
        } else {
            view.refreshActorInfo(searchResultDH.getSearchResultPerson().id);
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
