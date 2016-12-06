package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.SearchResultDH;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

/**
 * Created by Lynx on 12/2/2016.
 */

public class SearchResultHeaderVH extends RecyclerVH<SearchResultDH> {

    private TextView tvSearchHeader_LISH;

    public SearchResultHeaderVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvSearchHeader_LISH = findView(R.id.tvSearchHeader_LISH);
    }

    @Override
    public void bindData(SearchResultDH data) {
        tvSearchHeader_LISH.setText(data.getTitle());
    }
}
