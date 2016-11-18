package com.lynx.cinerama.presentation.screens.actors.scenes;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.holders.data.SceneDH;
import com.lynx.cinerama.presentation.screens.actors.ActorsActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/16/2016.
 */

@EFragment(R.layout.fragment_actor_scenes)
public class ActorScenesFragment extends BaseFragment<ActorsActivity> implements ActorScenesContract.ActorScenesView {

    private ActorScenesContract.ActorScenesPresenter presenter;

    @FragmentArg
    protected int actorID;

    @ViewById
    protected RecyclerView rvActorScenes_FAS;

    @AfterInject
    protected void initPresenter() {

    }

    @AfterViews
    protected void initUI() {

    }

    @Override
    public void displayActorScenes(ArrayList<SceneDH> sceneDHs) {

    }

    @Override
    public void clickActorScene(View v, int pos) {

    }

    @Override
    public void displaySceneGallery(View v, int pos, int actorID) {

    }

    @Override
    public void setPresenter(ActorScenesContract.ActorScenesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null)
            presenter.unsubscribe();
    }
}
