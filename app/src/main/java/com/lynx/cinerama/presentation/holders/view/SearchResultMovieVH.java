package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.search.SearchResultMovie;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;
import com.lynx.cinerama.presentation.utils.Constants;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 12/2/2016.
 */

public class SearchResultMovieVH extends RecyclerVH<SearchResultDH> {

    private ImageView ivPosterSearch_LISM;
    private TextView tvTitleSearch_LISM;
    private TextView tvDateSearch_LISM;

    public SearchResultMovieVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        itemView.setOnClickListener(null);

        ivPosterSearch_LISM = findView(R.id.ivPosterSearch_LISM);
        tvTitleSearch_LISM = findView(R.id.tvTitleSearch_LISM);
        tvDateSearch_LISM = findView(R.id.tvDateSearch_LISM);

        if(listener != null) {
            RxView.clicks(itemView)
                    .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                    .subscribe(aVoid -> listener.onClick(itemView, getAdapterPosition(), viewType));
        }
    }

    @Override
    public void bindData(SearchResultDH data) {
        SearchResultMovie item = data.getSearchResultMovie();
        Glide.with(itemView.getContext())
                .load(Constants.BASE_IMAGE_URL + item.poster_path)
                .placeholder(R.drawable.placeholder_movie)
                .error(R.drawable.placeholder_movie)
                .into(ivPosterSearch_LISM);
        tvTitleSearch_LISM.setText(item.title);
        tvDateSearch_LISM.setText(item.release_date);
    }
}
