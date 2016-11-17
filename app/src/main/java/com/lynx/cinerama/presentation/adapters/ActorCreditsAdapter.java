package com.lynx.cinerama.presentation.adapters;

import android.text.TextUtils;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.ActorCreditsDH;
import com.lynx.cinerama.presentation.holders.view.ActorCastVH;
import com.lynx.cinerama.presentation.holders.view.ActorCreditHeaderVH;
import com.lynx.cinerama.presentation.holders.view.ActorCrewVH;
import com.michenko.simpleadapter.TypedRecyclerAdapter;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 11/17/2016.
 */

@EBean
public class ActorCreditsAdapter extends TypedRecyclerAdapter<ActorCreditsDH> {

    public static final int TYPE_HEADER         = 0;
    public static final int TYPE_ACTOR_CAST     = 1;
    public static final int TYPE_ACTOR_CREW     = 2;

    @Override
    protected void initViewTypes() {
        addType(TYPE_HEADER, R.layout.list_item_cast_header, ActorCreditHeaderVH.class);
        addType(TYPE_ACTOR_CAST, R.layout.list_item_actor_cast, ActorCastVH.class);
        addType(TYPE_ACTOR_CREW, R.layout.list_item_actor_crew, ActorCrewVH.class);
    }

    @Override
    protected int getViewType(int position) {
        ActorCreditsDH creditsDH = getItem(position);
        if(!TextUtils.isEmpty(creditsDH.getTitle()))
            return TYPE_HEADER;
        else if(creditsDH.getActorCreditCast() != null)
            return TYPE_ACTOR_CAST;
        else
        return TYPE_ACTOR_CREW;
    }
}
