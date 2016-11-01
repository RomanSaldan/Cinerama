package com.lynx.cinerama.presentation.adapters;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.PosterDH;
import com.lynx.cinerama.presentation.holders.view.PosterVH;
import com.michenko.simpleadapter.SimpleRecyclerAdapter;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 11/1/2016.
 */

@EBean
public class PostersAdapter extends SimpleRecyclerAdapter<PosterDH, PosterVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.list_item_poster;
    }
}
