package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.SceneDH;
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

    }
}
