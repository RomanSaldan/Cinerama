package com.lynx.cinerama.presentation.screens.actors.images;

import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.screens.actors.ActorsActivity;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by Lynx on 11/16/2016.
 */

@EFragment
public class ActorImagesFragment extends BaseFragment<ActorsActivity> {

    @FragmentArg
    protected int actorID;

}
