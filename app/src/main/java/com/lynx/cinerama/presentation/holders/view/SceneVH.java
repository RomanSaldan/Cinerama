package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.SceneDH;
import com.lynx.cinerama.presentation.utils.Constants;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 11/1/2016.
 */

public class SceneVH extends RecyclerVH<SceneDH> {

    public ImageView ivMovieScene_LIS;

    public SceneVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        itemView.setOnClickListener(null);

        ivMovieScene_LIS = findView(R.id.ivMovieScene_LIS);
        if(listener != null) {
            RxView.clicks(ivMovieScene_LIS)
                    .throttleFirst(Constants.DELAY_CLICK_ANIMATION, TimeUnit.MILLISECONDS)
                    .subscribe(aVoid -> listener.onClick(ivMovieScene_LIS, getAdapterPosition(), viewType));
        }
    }

    @Override
    public void bindData(SceneDH data) {
        Glide.with(itemView.getContext())
                .load(Constants.BASE_IMAGE_URL + data.getImageModel().file_path)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .centerCrop()
                .into(ivMovieScene_LIS);
    }
}
