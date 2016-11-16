package com.lynx.cinerama.presentation.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lynx.cinerama.data.model.actors.ResponseActorInfo;
import com.lynx.cinerama.presentation.screens.actors.credits.ActorCreditsFragment;
import com.lynx.cinerama.presentation.screens.actors.credits.ActorCreditsFragment_;
import com.lynx.cinerama.presentation.screens.actors.images.ActorImagesFragment;
import com.lynx.cinerama.presentation.screens.actors.images.ActorImagesFragment_;
import com.lynx.cinerama.presentation.screens.actors.info.ActorInfoFragment;
import com.lynx.cinerama.presentation.screens.actors.info.ActorInfoFragment_;
import com.lynx.cinerama.presentation.screens.actors.scenes.ActorScenesFragment;
import com.lynx.cinerama.presentation.screens.actors.scenes.ActorScenesFragment_;

import java.util.ArrayList;

/**
 * Created by Lynx on 11/16/2016.
 */

public class ActorsTabAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> tabFragments;
    private Context mCtx;

    public ActorsTabAdapter(Context context, FragmentManager fm, ResponseActorInfo responseActorInfo) {
        super(fm);
        mCtx = context;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    private ArrayList<Fragment> getTabFragments(ResponseActorInfo data) {
        ArrayList<Fragment> result = new ArrayList<>();

        ActorInfoFragment actorInfoFragment = ActorInfoFragment_.builder().actorID(data.id).build();
        result.add(actorInfoFragment);

        if(data.movie_credits != null
                && (data.movie_credits.cast != null && data.movie_credits.cast.size() > 0)
                || (data.movie_credits.crew != null && data.movie_credits.crew.size() > 0)) {
            ActorCreditsFragment actorCreditsFragment = ActorCreditsFragment_.builder().actorID(data.id).build();
            result.add(actorCreditsFragment);
        }
        if(data.images != null && data.images.profiles != null && data.images.profiles.size() > 0) {
            ActorImagesFragment actorImagesFragment = ActorImagesFragment_.builder().actorID(data.id).build();
            result.add(actorImagesFragment);
        }
        if(data.tagged_images != null && data.tagged_images.results != null && data.tagged_images.results.size() > 0) {
            ActorScenesFragment actorScenesFragment = ActorScenesFragment_.builder().actorID(data.id).build();
            result.add(actorScenesFragment);
        }

        return result;
    }
}
