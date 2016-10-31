package com.lynx.cinerama.presentation.adapters;

import android.view.View;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.base.recycler.BaseRecyclerAdapter;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;
import com.lynx.cinerama.presentation.holders.view.ReviewVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 10/28/2016.
 */

@EBean
public class ReviewsAdapter extends BaseRecyclerAdapter<ReviewDH, ReviewVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.list_item_review;
    }

    @Override
    protected ReviewVH getViewHolder(View view) {
        return new ReviewVH(view);
    }
}
