package com.lynx.cinerama.presentation.screens.actors.info;

import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.screens.actors.ActorsActivity;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by Lynx on 11/16/2016.
 */

@EFragment
public class ActorInfoFragment extends BaseFragment<ActorsActivity> {

    @FragmentArg
    protected int actorID;

}
