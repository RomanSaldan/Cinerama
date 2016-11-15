package com.lynx.cinerama.presentation.holders.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.bumptech.glide.Glide;
import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.CreditsDH;
import com.lynx.cinerama.presentation.utils.Constants;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;

/**
 * Created by Lynx on 11/1/2016.
 */

public class CreditPersonVH extends RecyclerVH<CreditsDH> {

    private ImageView ivPortrait_LICH;
    private TextView tvName_LICH;
    private TextView tvRole_LICH;
    private RippleView rippleCastContainer_LIC;

    public CreditPersonVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        ivPortrait_LICH = findView(R.id.ivPortrait_LICH);
        tvName_LICH = findView(R.id.tvName_LICH);
        tvRole_LICH = findView(R.id.tvRole_LICH);
        rippleCastContainer_LIC = findView(R.id.rippleCastContainer_LIC);
        if (listener != null)
            rippleCastContainer_LIC.setOnClickListener(view -> listener.onClick(view, getAdapterPosition(), viewType));
    }

    @Override
    public void bindData(CreditsDH data) {
        if (data.getPersonCast() != null) {
            Glide.with(itemView.getContext())
                    .load(Constants.BASE_IMAGE_URL + data.getPersonCast().profile_path)
                    .placeholder(R.drawable.placeholder_portrait)
                    .error(R.drawable.placeholder_portrait)
                    .into(ivPortrait_LICH);
            tvName_LICH.setText(data.getPersonCast().name);
            tvRole_LICH.setText(data.getPersonCast().character);
        } else {
            Glide.with(itemView.getContext())
                    .load(Constants.BASE_IMAGE_URL + data.getPersonCrew().profile_path)
                    .placeholder(R.drawable.placeholder_portrait)
                    .error(R.drawable.placeholder_portrait)
                    .into(ivPortrait_LICH);
            tvName_LICH.setText(data.getPersonCrew().name);
            tvRole_LICH.setText(data.getPersonCrew().job);

        }
    }
}
