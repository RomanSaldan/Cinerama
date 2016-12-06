package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.R;
import com.lynx.cinerama.data.model.movies.similar.ShortMovieInfo;
import com.lynx.cinerama.data.model.search.SearchResultPerson;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;
import com.lynx.cinerama.presentation.utils.Constants;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 12/2/2016.
 */

public class SearchResultPersonVH extends RecyclerVH<SearchResultDH> {

    private ImageView ivPortraitSearch_LISA;
    private TextView tvNameSearch_LISA;
    private TextView tvKnownForSearch_LISA;

    public SearchResultPersonVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);
        itemView.setOnClickListener(null);

        ivPortraitSearch_LISA = findView(R.id.ivPortraitSearch_LISA);
        tvNameSearch_LISA = findView(R.id.tvNameSearch_LISA);
        tvKnownForSearch_LISA = findView(R.id.tvKnownForSearch_LISA);

        if(listener != null) {
            RxView.clicks(itemView)
                    .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                    .subscribe(aVoid -> listener.onClick(itemView, getAdapterPosition(), viewType));
        }
    }

    @Override
    public void bindData(SearchResultDH data) {
        SearchResultPerson item = data.getSearchResultPerson();
        Glide.with(itemView.getContext())
                .load(Constants.BASE_IMAGE_URL + item.profile_path)
                .placeholder(R.drawable.placeholder_portrait)
                .error(R.drawable.placeholder_portrait)
                .into(ivPortraitSearch_LISA);
        tvNameSearch_LISA.setText(item.name);
        tvKnownForSearch_LISA.setText(getKnownFor(item));
    }

    private String getKnownFor(SearchResultPerson searchResultPerson) {
        ArrayList<String> titles = new ArrayList<>();
        if(searchResultPerson.known_for != null && searchResultPerson.known_for.size() > 0) {
            titles = new ArrayList<>();
            for (ShortMovieInfo shortMovieInfo : searchResultPerson.known_for)
                titles.add(shortMovieInfo.title);
        }
        return TextUtils.join(", ", titles);
    }
}
