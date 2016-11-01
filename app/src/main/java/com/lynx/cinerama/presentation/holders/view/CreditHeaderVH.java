package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.CreditsDH;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

/**
 * Created by Lynx on 11/1/2016.
 */

public class CreditHeaderVH extends RecyclerVH<CreditsDH> {

    private TextView tvTitle_LICH;

    public CreditHeaderVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvTitle_LICH = findView(R.id.tvTitle_LICH);
    }

    @Override
    public void bindData(CreditsDH data) {
        tvTitle_LICH.setText(data.getTitle());
    }
}
