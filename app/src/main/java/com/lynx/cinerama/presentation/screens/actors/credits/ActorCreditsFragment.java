package com.lynx.cinerama.presentation.screens.actors.credits;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.screens.actors.ActorsActivity;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by Lynx on 11/16/2016.
 */

@EFragment(R.layout.fragment_actor_credits)
public class ActorCreditsFragment extends BaseFragment<ActorsActivity> {

    @FragmentArg
    protected int actorID;

}