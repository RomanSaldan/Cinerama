package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.ActorCreditsDH;
import com.lynx.cinerama.presentation.utils.Constants;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 11/17/2016.
 */

public class ActorCastVH extends RecyclerVH<ActorCreditsDH> {

    private RippleView rippleCastContainer_LIAC;
    private ImageView ivPoster_LIAC;
    private TextView tvMovieTitle_LIAC;
    private TextView tvActorRole_LIAC;
    private TextView tvMovieDate_LIAC;

    public ActorCastVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        rippleCastContainer_LIAC = findView(R.id.rippleCastContainer_LIAC);
        ivPoster_LIAC = findView(R.id.ivPoster_LIAC);
        tvMovieTitle_LIAC = findView(R.id.tvMovieTitle_LIAC);
        tvActorRole_LIAC = findView(R.id.tvActorRole_LIAC);
        tvMovieDate_LIAC = findView(R.id.tvMovieDate_LIAC);

        if(listener != null)
            RxView.clicks(rippleCastContainer_LIAC)
                    .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                    .subscribe(aVoid -> listener.onClick(itemView, getAdapterPosition(), viewType));
    }

    @Override
    public void bindData(ActorCreditsDH data) {
        if(data.getActorCreditCast() != null) {
            Glide.with(itemView.getContext())
                    .load(Constants.BASE_IMAGE_URL + data.getActorCreditCast().poster_path)
                    .centerCrop()
                    .error(R.drawable.placeholder_movie)
                    .placeholder(R.drawable.placeholder_movie)
                    .into(ivPoster_LIAC);
            if(!TextUtils.isEmpty(data.getActorCreditCast().title))
                tvMovieTitle_LIAC.setText(data.getActorCreditCast().title);
            if(!TextUtils.isEmpty(data.getActorCreditCast().character))
                tvActorRole_LIAC.setText(data.getActorCreditCast().character);
            if(!TextUtils.isEmpty(data.getActorCreditCast().release_date))
                tvMovieDate_LIAC.setText(data.getActorCreditCast().release_date);
        }
    }
}
