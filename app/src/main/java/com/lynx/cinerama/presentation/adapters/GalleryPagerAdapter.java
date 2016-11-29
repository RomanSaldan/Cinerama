package com.lynx.cinerama.presentation.adapters;

import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.gallery.ImageModel;
import com.lynx.cinerama.presentation.utils.Constants;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Lynx on 11/2/2016.
 */

@EBean
public class GalleryPagerAdapter extends PagerAdapter {

    private ArrayList<ImageModel> data = new ArrayList<>();
    private PhotoViewAttacher photoViewAttacher;
    private PhotoViewAttacher.OnViewTapListener onViewTapListener;

    private AppCompatActivity compatActivity;
    private int pos;

    public void updateData(List<ImageModel> imageModels) {
        data.addAll(imageModels);
        notifyDataSetChanged();
    }

    public void setTransitionRequisites(AppCompatActivity activity, int startPosition) {
        compatActivity = activity;
        pos = startPosition;
    }

    public ImageModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.layout_gallery_item, container, false);
        ProgressBar pbImageDownload_LGI = (ProgressBar) view.findViewById(R.id.pbImageDownload_LGI);
        ImageView ivFullscreenImage_LGI = (ImageView) view.findViewById(R.id.ivFullscreenImage_LGI);

        if(pos == position && android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            ivFullscreenImage_LGI.setTransitionName("gallery" + pos);
        pbImageDownload_LGI.setVisibility(View.VISIBLE);

        DrawableRequestBuilder<String> thumbnailRequest = Glide
                .with(container.getContext())
                .load(Constants.BASE_IMAGE_URL + data.get(position).file_path)
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        pbImageDownload_LGI.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        pbImageDownload_LGI.setVisibility(View.GONE);
                        return false;
                    }
                });

        Glide.with(container.getContext())
                .load(Constants.BASE_LARGE_IMAGE_URL + data.get(position).file_path)
                .thumbnail(thumbnailRequest)
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        pbImageDownload_LGI.setVisibility(View.GONE);
                        if(position == pos && android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                            compatActivity.startPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if(position == pos && android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                            compatActivity.startPostponedEnterTransition();
                        pbImageDownload_LGI.setVisibility(View.GONE);
                        photoViewAttacher = new PhotoViewAttacher(ivFullscreenImage_LGI, true);
                        photoViewAttacher.setOnViewTapListener(onViewTapListener);
                        return false;
                    }
                })
                .into(ivFullscreenImage_LGI);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener onViewTapListener) {
        this.onViewTapListener = onViewTapListener;
    }

}
