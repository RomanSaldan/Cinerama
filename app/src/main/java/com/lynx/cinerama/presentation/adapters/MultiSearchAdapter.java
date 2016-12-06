package com.lynx.cinerama.presentation.adapters;

import android.text.TextUtils;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;
import com.lynx.cinerama.presentation.holders.view.SearchResultHeaderVH;
import com.lynx.cinerama.presentation.holders.view.SearchResultMovieVH;
import com.lynx.cinerama.presentation.holders.view.SearchResultPersonVH;
import com.michenko.simpleadapter.TypedRecyclerAdapter;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

/**
 * Created by Lynx on 12/2/2016.
 */

@EBean
public class MultiSearchAdapter extends TypedRecyclerAdapter<SearchResultDH> {

    public static final int TYPE_HEADER             = 0;
    public static final int TYPE_RESULT_MOVIE       = 1;
    public static final int TYPE_RESULT_ACTOR       = 2;

    @Override
    protected void initViewTypes() {
        addType(TYPE_HEADER, R.layout.list_item_search_header, SearchResultHeaderVH.class);
        addType(TYPE_RESULT_MOVIE, R.layout.list_item_search_movies, SearchResultMovieVH.class);
        addType(TYPE_RESULT_ACTOR, R.layout.list_item_search_actors, SearchResultPersonVH.class);
    }

    @Override
    protected int getViewType(int position) {
        SearchResultDH item = getItem(position);
        if(!TextUtils.isEmpty(item.getTitle()))
            return TYPE_HEADER;
        else if(item.getSearchResultMovie() != null)
            return TYPE_RESULT_MOVIE;
        else
            return TYPE_RESULT_ACTOR;
    }

    public void clear() {
        setListDH(new ArrayList<>());
    }
}
