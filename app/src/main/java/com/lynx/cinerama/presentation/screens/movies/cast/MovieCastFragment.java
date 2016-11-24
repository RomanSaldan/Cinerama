package com.lynx.cinerama.presentation.screens.movies.cast;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lynx.cinerama.R;
import com.lynx.cinerama.domain.MovieRepository;
import com.lynx.cinerama.presentation.adapters.MovieCastAdapter;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.holders.data.MovieCastDH;
import com.lynx.cinerama.presentation.screens.actors.ActorsActivity_;
import com.lynx.cinerama.presentation.screens.movies.MoviesActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

@EFragment(R.layout.fragment_movie_cast)
public class MovieCastFragment extends BaseFragment<MoviesActivity> implements MovieCastContract.MovieCastView {

    private MovieCastContract.MovieCastPresenter presenter;
    private GridLayoutManager glm;

    @FragmentArg
    protected int movieID;

    @Bean
    protected MovieCastAdapter movieCastAdapter;

    @Bean
    protected MovieRepository movieRepository;

    @ViewById
    protected RecyclerView rvCredits_FMC;

    @AfterInject
    protected void initPresenter() {
        new MovieCastPresenter(this, movieID, movieRepository);
    }

    @AfterViews
    protected void initUI() {
        final Configuration configuration = getResources().getConfiguration();
        int spanCount = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1;
        glm = new GridLayoutManager(getActivity(), spanCount, LinearLayoutManager.VERTICAL, false);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int headerCount = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1;
                return movieCastAdapter.getItemViewType(position) == MovieCastAdapter.TYPE_HEADER ? headerCount : 1;
            }
        });
        rvCredits_FMC.setLayoutManager(glm);
        rvCredits_FMC.setAdapter(movieCastAdapter);
        movieCastAdapter.setOnCardClickListener((view, position, viewType) -> {
          if(movieCastAdapter.getItem(position).getPersonCast() != null)
              clickCreditPerson(movieCastAdapter.getItem(position).getPersonCast().id);
            else if(movieCastAdapter.getItem(position).getPersonCrew() != null)
              clickCreditPerson(movieCastAdapter.getItem(position).getPersonCrew().id);
        });

        presenter.subscribe();
    }

    @Override
    public void displayCredits(ArrayList<MovieCastDH> movieCastDHs) {
        movieCastAdapter.setListDH(movieCastDHs);
    }

    @Override
    public void clickCreditPerson(int personID) {
        presenter.setupPersonInfoScreen(personID);
    }

    @Override
    public void openPersonInfoScreen(int personID) {
        ActorsActivity_.intent(getActivity())
                .actorID(personID)
                .start();
        getActivity().finish();
    }

    @Override
    public void setPresenter(MovieCastContract.MovieCastPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        glm.setSpanCount(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null)
            presenter.unsubscribe();
    }
}
