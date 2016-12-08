package com.lynx.cinerama.presentation.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.ResponseMovieInfo;
import com.lynx.cinerama.presentation.screens.movie_item.cast.MovieCastFragment;
import com.lynx.cinerama.presentation.screens.movie_item.cast.MovieCastFragment_;
import com.lynx.cinerama.presentation.screens.movie_item.info.MovieInfoFragment;
import com.lynx.cinerama.presentation.screens.movie_item.info.MovieInfoFragment_;
import com.lynx.cinerama.presentation.screens.movie_item.posters.MoviePostersFragment;
import com.lynx.cinerama.presentation.screens.movie_item.posters.MoviePostersFragment_;
import com.lynx.cinerama.presentation.screens.movie_item.scenes.MovieScenesFragment;
import com.lynx.cinerama.presentation.screens.movie_item.scenes.MovieScenesFragment_;
import com.lynx.cinerama.presentation.screens.movie_item.videos.MovieVideosFragment;
import com.lynx.cinerama.presentation.screens.movie_item.videos.MovieVideosFragment_;

import java.util.ArrayList;

/**
 * Created by Lynx on 10/26/2016.
 */

public class MoviesTabAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> tabFragments;
    private Context mCtx;

    public MoviesTabAdapter(Context context, FragmentManager fm, ResponseMovieInfo movieInfo) {
        super(fm);
        mCtx = context;
        tabFragments    = getTabFragments(movieInfo);
    }

    @Override
    public Fragment getItem(int position) {
        return tabFragments.get(position);
    }

    @Override
    public int getCount() {
        return tabFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Fragment current = tabFragments.get(position);
        if (current instanceof MovieInfoFragment)
            return mCtx.getString(R.string.tab_title_info);
        else if (current instanceof MovieCastFragment)
            return mCtx.getString(R.string.tab_title_cast);
        else if(current instanceof MovieScenesFragment)
            return mCtx.getString(R.string.tab_title_scenes);
        else if(current instanceof MoviePostersFragment)
            return mCtx.getString(R.string.tab_title_posters);
        else if (current instanceof MovieVideosFragment)
            return mCtx.getString(R.string.tab_title_videos);
        else
            return "";
    }

    private ArrayList<Fragment> getTabFragments(ResponseMovieInfo data) {
        ArrayList<Fragment> result = new ArrayList<>();

        MovieInfoFragment movieInfoFragment = MovieInfoFragment_.builder().movieID(data.id).build();
        result.add(movieInfoFragment);

        if(data.credits.cast.size() > 0 || data.credits.crew.size() > 0) {
            MovieCastFragment movieCastFragment = MovieCastFragment_.builder().movieID(data.id).build();
            result.add(movieCastFragment);
        }
        if(data.images.backdrops.size() > 0) {
            MovieScenesFragment movieScreensFragment = MovieScenesFragment_.builder().movieID(data.id).build();
            result.add(movieScreensFragment);
        }
        if(data.images.posters.size() > 0) {
            MoviePostersFragment moviePostersFragment = MoviePostersFragment_.builder().movieID(data.id).build();
            result.add(moviePostersFragment);
        }
        if(data.videos.results.size() > 0) {
            MovieVideosFragment movieVideosFragment = MovieVideosFragment_.builder().movieID(data.id).build();
            result.add(movieVideosFragment);
        }

        return result;
    }
}
