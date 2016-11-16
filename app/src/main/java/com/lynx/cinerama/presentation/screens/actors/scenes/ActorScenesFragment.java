package com.lynx.cinerama.presentation.screens.actors.scenes;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.screens.actors.ActorsActivity;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by Lynx on 11/16/2016.
 */

@EFragment(R.layout.fragment_actor_scenes)
public class ActorScenesFragment extends BaseFragment<ActorsActivity> {

    @FragmentArg
    protected int actorID;

}
