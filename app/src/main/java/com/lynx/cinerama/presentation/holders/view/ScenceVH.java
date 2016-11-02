package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.SceneDH;
import com.lynx.cinerama.presentation.utils.Constants;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

/**
 * Created by Lynx on 11/1/2016.
 */

public class ScenceVH extends RecyclerVH<SceneDH> {

    public ImageView ivMovieScene_LIS;

    public ScenceVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        ivMovieScene_LIS = findView(R.id.ivMovieScene_LIS);
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
