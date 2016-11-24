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

public class ActorCrewVH extends RecyclerVH<ActorCreditsDH> {

    private RippleView rippleCastContainer_LIACW;
    private TextView tvMovieTitle_LIACW;
    private TextView tvActorJob_LIACW;
    private TextView tvMovieDate_LIACW;
    private ImageView ivPoster_LIACW;

    public ActorCrewVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        rippleCastContainer_LIACW = findView(R.id.rippleCastContainer_LIACW);
        tvMovieTitle_LIACW = findView(R.id.tvMovieTitle_LIACW);
        tvActorJob_LIACW = findView(R.id.tvActorJob_LIACW);
        tvMovieDate_LIACW = findView(R.id.tvMovieDate_LIACW);
        ivPoster_LIACW = findView(R.id.ivPoster_LIACW);

        if(listener != null)
            RxView.clicks(rippleCastContainer_LIACW)
                    .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                    .subscribe(aVoid -> listener.onClick(itemView, getAdapterPosition(), viewType));
    }

    @Override
    public void bindData(ActorCreditsDH data) {
        if(data.getActorCreditCrew() != null) {
            Glide.with(itemView.getContext())
                    .load(Constants.BASE_IMAGE_URL + data.getActorCreditCrew().poster_path)
                    .centerCrop()
                    .error(R.drawable.placeholder_movie)
                    .error(R.drawable.placeholder_movie)
                    .into(ivPoster_LIACW);
            if(!TextUtils.isEmpty(data.getActorCreditCrew().title))
                tvMovieTitle_LIACW.setText(data.getActorCreditCrew().title);
            if(!TextUtils.isEmpty(data.getActorCreditCrew().job))
                tvActorJob_LIACW.setText(data.getActorCreditCrew().job);
            if(!TextUtils.isEmpty(data.getActorCreditCrew().release_date))
                tvMovieDate_LIACW.setText(data.getActorCreditCrew().release_date);
        }
    }
}
