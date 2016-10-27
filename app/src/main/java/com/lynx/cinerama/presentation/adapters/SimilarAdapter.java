package com.lynx.cinerama.presentation.adapters;

import android.view.View;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.base.recycler.BaseRecyclerAdapter;
import com.lynx.cinerama.presentation.holders.data.SimilarDH;
import com.lynx.cinerama.presentation.holders.view.SimilarVH;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 10/27/2016.
 */

@EBean
public class SimilarAdapter extends BaseRecyclerAdapter<SimilarDH, SimilarVH>{

    @Override
    protected int getItemLayout() {
        return R.layout.list_item_similar;
    }

    @Override
    protected SimilarVH getViewHolder(View view) {
        return new SimilarVH(view);
    }
}
