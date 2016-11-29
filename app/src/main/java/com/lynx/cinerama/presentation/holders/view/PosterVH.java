package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.PosterDH;
import com.lynx.cinerama.presentation.utils.Constants;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 11/1/2016.
 */

public class PosterVH extends RecyclerVH<PosterDH> {

    public ImageView ivPoster_LIP;

    public PosterVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        itemView.setOnClickListener(null);

        ivPoster_LIP = findView(R.id.ivPoster_LIP);
        if(listener != null) {
            RxView.clicks(ivPoster_LIP)
                    .throttleFirst(Constants.DELAY_CLICK_ANIMATION, TimeUnit.MILLISECONDS)
                    .subscribe(aVoid -> listener.onClick(ivPoster_LIP, getAdapterPosition(), viewType));
        }
    }

    @Override
    public void bindData(PosterDH data) {
        Glide.with(itemView.getContext())
                .load(Constants.BASE_IMAGE_URL + data.getData().file_path)
                .placeholder(R.drawable.placeholder_poster)
                .error(R.drawable.placeholder_poster)
//                .centerCrop()
                .into(ivPoster_LIP);
    }
}
