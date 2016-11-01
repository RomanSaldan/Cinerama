package com.lynx.cinerama.presentation.adapters;

import android.text.TextUtils;

import com.lynx.cinerama.R;
import com.lynx.cinerama.presentation.holders.data.CreditsDH;
import com.lynx.cinerama.presentation.holders.view.CreditHeaderVH;
import com.lynx.cinerama.presentation.holders.view.CreditPersonVH;
import com.michenko.simpleadapter.TypedRecyclerAdapter;

import org.androidannotations.annotations.EBean;

/**
 * Created by Lynx on 11/1/2016.
 */

@EBean
public class CreditsAdapter extends TypedRecyclerAdapter<CreditsDH> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_PERSON = 1;

    @Override
    protected void initTypes() {
        addType(TYPE_HEADER, R.layout.list_item_cast_header, CreditHeaderVH.class);
        addType(TYPE_PERSON, R.layout.list_item_cast, CreditPersonVH.class);
    }

    @Override
    protected int getViewType(int position) {
        CreditsDH dh = getItem(position);
        if(!TextUtils.isEmpty(dh.getTitle()))
            return TYPE_HEADER;
        else
            return TYPE_PERSON;
    }
}