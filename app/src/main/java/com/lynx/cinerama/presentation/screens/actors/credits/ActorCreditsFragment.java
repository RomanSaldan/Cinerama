package com.lynx.cinerama.presentation.screens.actors.credits;

import android.content.res.Configuration;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lynx.cinerama.R;
import com.lynx.cinerama.domain.ActorRepository;
import com.lynx.cinerama.presentation.adapters.ActorCreditsAdapter;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.holders.data.ActorCreditsDH;
import com.lynx.cinerama.presentation.screens.actors.ActorsActivity;
import com.lynx.cinerama.presentation.screens.movies.MoviesActivity_;
import com.lynx.cinerama.presentation.screens.movies.info.fullscreen_poster.FullscreenPosterActivity_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/16/2016.
 */

@EFragment(R.layout.fragment_actor_credits)
public class ActorCreditsFragment extends BaseFragment<ActorsActivity> implements ActorCreditsContract.ActorCreditsView {

    private ActorCreditsContract.ActorCreditsPresenter presenter;
    private GridLayoutManager glm;

    @FragmentArg
    protected int actorID;

    @ViewById
    protected RecyclerView rvCredits_FAC;

    @StringRes(R.string.key_fullscreen_poster)
    protected String keyFullscreenPoster;

    @Bean
    protected ActorRepository actorRepository;

    @Bean
    protected ActorCreditsAdapter actorCreditsAdapter;

    @AfterInject
    protected void initPresenter() {
        new ActorCreditsPresenter(this, actorRepository, actorID);
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
                return actorCreditsAdapter.getItemViewType(position) == actorCreditsAdapter.TYPE_HEADER ? headerCount : 1;
            }
        });
        rvCredits_FAC.setLayoutManager(glm);
        rvCredits_FAC.setAdapter(actorCreditsAdapter);

        actorCreditsAdapter.setOnCardClickListener((view, position, viewType) -> {
            ActorCreditsDH item = actorCreditsAdapter.getItem(position);
            clickActorCredit(item.getActorCreditCast() == null ? item.getActorCreditCrew().id : item.getActorCreditCast().id);
        });

        presenter.subscribe();
    }

    @Override
    public void displayActorCredits(ArrayList<ActorCreditsDH> creditsDHs) {
        actorCreditsAdapter.setListDH(creditsDHs);
    }

    @Override
    public void clickActorCredit(int movieID) {
        presenter.setupMovieInfoScreen(movieID);
    }

    @Override
    public void openMovieScreen(int movieID) {
        MoviesActivity_.intent(getActivity())
                .movieID(movieID)
                .start();
        getActivity().finish();
    }

    @Override
    public void setPresenter(ActorCreditsContract.ActorCreditsPresenter presenter) {
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
