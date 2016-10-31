package com.lynx.cinerama.presentation.adapters;

import android.view.View;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.listeners.OnReviewDetailsListener;
import com.lynx.cinerama.presentation.base.recycler.BaseRecyclerAdapter;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;
import com.lynx.cinerama.presentation.holders.view.ReviewDetailsVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 10/28/2016.
 */

@EBean
public class ReviewDetailsAdapter extends BaseRecyclerAdapter<ReviewDH, ReviewDetailsVH> {

    private OnReviewDetailsListener onReviewDetailsListener;

    public void setOnReviewDetailsListener(OnReviewDetailsListener onReviewDetailsListener) {
        this.onReviewDetailsListener = onReviewDetailsListener;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.list_item_review;
    }

    @Override
    protected ReviewDetailsVH getViewHolder(View view) {
        return new ReviewDetailsVH(view, onReviewDetailsListener);
    }
}
