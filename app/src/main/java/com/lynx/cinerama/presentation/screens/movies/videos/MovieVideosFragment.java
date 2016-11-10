package com.lynx.cinerama.presentation.screens.movies.videos;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.base.BaseFragment;
import com.lynx.cinerama.presentation.screens.movies.MoviesActivity;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by Lynx on 10/26/2016.
 */

@EFragment(R.layout.fragment_movie_videos)
public class MovieVideosFragment extends BaseFragment<MoviesActivity> {

    @FragmentArg
    protected int movieID;

}
