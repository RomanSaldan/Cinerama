package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.SimilarDH;
import com.lynx.cinerama.presentation.utils.Constants;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

/**
 * Created by Lynx on 10/27/2016.
 */

public class SimilarVH extends RecyclerVH<SimilarDH> {

    private ImageView ivPoster_LIS;
    private TextView tvSimilarTitle_LIS;
    private TextView tvRatingSimilar_LIS;
    private TextView tvSimilarYear_LIS;

    public SimilarVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        ivPoster_LIS = findView(R.id.ivPoster_LIS);
        tvSimilarTitle_LIS = findView(R.id.tvSimilarTitle_LIS);
        tvRatingSimilar_LIS = findView(R.id.tvRatingSimilar_LIS);
        tvSimilarYear_LIS = findView(R.id.tvSimilarYear_LIS);
    }

    @Override
    public void bindData(SimilarDH data) {
        Glide.with(tvRatingSimilar_LIS.getContext())
                .load(Constants.BASE_IMAGE_URL + data.movieSimilar.poster_path)
                .centerCrop()
                .into(ivPoster_LIS);
        tvSimilarTitle_LIS.setText(TextUtils.isEmpty(data.movieSimilar.title) ? "" : data.movieSimilar.title);
        tvRatingSimilar_LIS.setText(data.movieSimilar.vote_average > 0 ? String.valueOf(data.movieSimilar.vote_average) : "");
        tvSimilarYear_LIS.setText(!TextUtils.isEmpty(data.movieSimilar.release_date) ? data.movieSimilar.release_date.split("-")[0] : "");
    }
}
