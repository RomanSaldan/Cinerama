package com.lynx.cinerama.presentation.holders.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.listeners.OnReviewDetailsListener;
import com.lynx.cinerama.presentation.base.recycler.BaseVH;
import com.lynx.cinerama.presentation.holders.data.ReviewDH;
import com.lynx.cinerama.presentation.utils.Constants;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 10/28/2016.
 */

public class ReviewDetailsVH extends BaseVH<ReviewDH> {

    private OnReviewDetailsListener onReviewDetailsListener;

    protected View divider_LIR;
    protected TextView tvReviewAuthorName_LIR;
    protected TextView tvReview_LIR;
    protected ImageView ivLink_LIR;

    public ReviewDetailsVH(View itemView, OnReviewDetailsListener onReviewDetailsListener) {
        super(itemView);
        this.onReviewDetailsListener = onReviewDetailsListener;

        divider_LIR = findView(R.id.divider_LIR);
        tvReviewAuthorName_LIR = findView(R.id.tvReviewAuthorName_LIR);
        tvReview_LIR = findView(R.id.tvReview_LIR);
        ivLink_LIR = findView(R.id.ivLink_LIR);

        divider_LIR.setVisibility(View.VISIBLE);
        tvReview_LIR.setMaxLines(Integer.MAX_VALUE);
    }

    @Override
    public void bindData(ReviewDH data) {
        tvReviewAuthorName_LIR.setText(data.movieReview.author);
        tvReview_LIR.setText(data.movieReview.content);
        ivLink_LIR.setVisibility(TextUtils.isEmpty(data.movieReview.url) ? View.GONE : View.VISIBLE);

        RxView.clicks(ivLink_LIR)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> onReviewDetailsListener.clickReviewDetails(data.movieReview.url));
    }
}
