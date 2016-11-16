package com.lynx.cinerama.presentation.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lynx.cinerama.R;
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
        tabFragments = getTabFragments(responseActorInfo);
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
        if (current instanceof ActorInfoFragment)
            return mCtx.getString(R.string.tab_title_info);
        else if (current instanceof ActorCreditsFragment)
            return mCtx.getString(R.string.tab_title_credits);
        else if(current instanceof ActorImagesFragment)
            return mCtx.getString(R.string.tab_title_images);
        else if(current instanceof ActorScenesFragment)
            return mCtx.getString(R.string.tab_title_scenes);
        else
            return "";
    }

    private ArrayList<Fragment> getTabFragments(ResponseActorInfo data) {
        ArrayList<Fragment> result = new ArrayList<>();

        ActorInfoFragment actorInfoFragment = ActorInfoFragment_.builder().actorID(data.id).build();
        result.add(actorInfoFragment);

        if(data.credits != null
                && ((data.credits.cast != null && data.credits.cast.size() > 0)
                || (data.credits.crew != null && data.credits.crew.size() > 0))) {
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
