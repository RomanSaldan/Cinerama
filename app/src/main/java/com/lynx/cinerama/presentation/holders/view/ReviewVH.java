package com.lynx.cinerama.presentation.holders.view;

import android.view.View;
import android.widget.TextView;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.base.recycler.BaseVH;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;

/**
 * Created by Lynx on 10/28/2016.
 */

public class ReviewVH extends BaseVH<ReviewDH> {

    public TextView tvReviewAuthorName_LIR;
    public TextView tvReview_LIR;

    public ReviewVH(View itemView) {
        super(itemView);

        tvReviewAuthorName_LIR = findView(R.id.tvReviewAuthorName_LIR);
        tvReview_LIR = findView(R.id.tvReview_LIR);
    }

    @Override
    public void bindData(ReviewDH data) {
        tvReviewAuthorName_LIR.setText(data.movieReview.author);
        tvReview_LIR.setText(data.movieReview.content);
    }
}
