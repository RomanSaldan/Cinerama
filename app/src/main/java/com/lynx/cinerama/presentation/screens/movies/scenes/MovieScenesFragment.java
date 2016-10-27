package com.lynx.cinerama.presentation.screens.movies.scenes;

import android.support.v4.app.Fragment;

import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.res.StringRes;

/**
 * Created by Lynx on 10/26/2016.
 */

@EFragment(R.layout.fragemnt_movie_scenes)
public class MovieScenesFragment extends Fragment {

    @FragmentArg
    protected ResponseMovieInfo responseMovieInfo;

}
