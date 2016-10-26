package com.lynx.cinerama.presentation.base.recycler;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.lynx.cinerama.presentation.utils.Constants;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 10/26/2016.
 */

public abstract class BaseVH <DH extends BaseDH> extends RecyclerView.ViewHolder {
    protected View parentView;

    public BaseVH(View itemView) {
        super(itemView);
        parentView = itemView;
    }

    public BaseVH(View itemView, OnClickCardListener listener) {
        super(itemView);
        parentView = itemView;
        RxView.clicks(parentView)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    if (listener != null) {
                        listener.onClickCard(getAdapterPosition() - 1);
                    }
                });
    }

    public abstract void bindData(DH data);

    protected <T extends View> T findView(@IdRes int viewId) {
        return (T) parentView.findViewById(viewId);
    }
}
