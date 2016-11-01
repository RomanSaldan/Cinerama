package com.lynx.cinerama.presentation.adapters;

import android.view.View;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.base.recycler.BaseRecyclerAdapter;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;
import com.lynx.cinerama.presentation.holders.view.ReviewVH;
import com.michenko.simpleadapter.SimpleRecyclerAdapter;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 10/28/2016.
 */

@EBean
public class ReviewsAdapter extends SimpleRecyclerAdapter<ReviewDH, ReviewVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.list_item_review;
    }

}
