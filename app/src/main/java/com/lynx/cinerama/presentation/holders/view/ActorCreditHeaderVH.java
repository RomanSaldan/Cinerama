package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.ActorCreditsDH;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

/**
 * Created by Lynx on 11/17/2016.
 */

public class ActorCreditHeaderVH extends RecyclerVH<ActorCreditsDH> {

    private TextView tvTitle_LICH;

    public ActorCreditHeaderVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvTitle_LICH = findView(R.id.tvTitle_LICH);
    }

    @Override
    public void bindData(ActorCreditsDH data) {
        if(!TextUtils.isEmpty(data.getTitle()))
            tvTitle_LICH.setText(data.getTitle());
    }
}
