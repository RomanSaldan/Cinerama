package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.base.recycler.BaseVH;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

/**
 * Created by Lynx on 10/28/2016.
 */

public class ReviewVH extends RecyclerVH<ReviewDH> {

    public TextView tvReviewAuthorName_LIR;
    public TextView tvReview_LIR;

    public ReviewVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvReviewAuthorName_LIR = findView(R.id.tvReviewAuthorName_LIR);
        tvReview_LIR = findView(R.id.tvReview_LIR);
    }

    @Override
    public void bindData(ReviewDH data) {
        tvReviewAuthorName_LIR.setText(data.movieReview.author);
        tvReview_LIR.setText(data.movieReview.content);
    }
}
