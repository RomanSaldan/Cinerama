package com.lynx.cinerama.presentation.holders.view;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.VideoDH;
import com.lynx.cinerama.presentation.utils.Constants;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

import java.util.concurrent.TimeUnit;

import static android.R.attr.data;

/**
 * Created by Lynx on 11/10/2016.
 */

public class VideoVH extends RecyclerVH<VideoDH> {

    private ImageView ivThumbnail_LIV;
    private TextView tvVideoTitle_LIV;

    public VideoVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        if(listener != null)
        RxView.clicks(itemView)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> listener.onClick(itemView, getAdapterPosition(), viewType));

        ivThumbnail_LIV = findView(R.id.ivThumbnail_LIV);
        tvVideoTitle_LIV = findView(R.id.tvVideoTitle_LIV);
    }

    @Override
    public void bindData(VideoDH data) {
        Glide.with(itemView.getContext())
                .load(String.format(Constants.URL_THUMBNAIL_PATTERN, data.getMovieVideo().key))
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Drawable[] layers = new Drawable[2];
                        Drawable foregroundDrawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.selector_bg_youtube);
                        layers[0] = resource;
                        layers[1] = foregroundDrawable;
                        LayerDrawable layerDrawable = new LayerDrawable(layers);
                        ivThumbnail_LIV.setImageDrawable(layerDrawable);
                        return true;
                    }
                })
                .into(ivThumbnail_LIV);
        tvVideoTitle_LIV.setText(data.getMovieVideo().name);
    }

}
