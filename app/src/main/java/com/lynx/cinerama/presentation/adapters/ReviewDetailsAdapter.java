package com.lynx.cinerama.presentation.adapters;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;
import com.lynx.cinerama.presentation.holders.view.ReviewDetailsVH;
import com.michenko.simpleadapter.SimpleRecyclerAdapter;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 10/28/2016.
 */

@EBean
public class ReviewDetailsAdapter extends SimpleRecyclerAdapter<ReviewDH, ReviewDetailsVH> {

    @Override
    protected int getItemLayout() {
        return R.layout.list_item_review;
    }

}
