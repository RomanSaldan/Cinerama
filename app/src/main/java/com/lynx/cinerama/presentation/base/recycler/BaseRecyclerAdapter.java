package com.lynx.cinerama.presentation.base.recycler;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lynx on 10/26/2016.
 */

public abstract class BaseRecyclerAdapter <DH extends BaseDH, VH extends BaseVH<DH>> extends RecyclerView.Adapter<VH> {
    protected OnClickCardListener onCardClickListener;
    protected ArrayList<DH> listDH = new ArrayList<>();

    public void setListDH(ArrayList<DH> listDH) {
        this.listDH = listDH;
        notifyDataSetChanged();
    }

    public void addMoreDHs(ArrayList<DH> dhs) {
        int lastPos = listDH.size();
        listDH.addAll(dhs);
        notifyItemRangeInserted(lastPos, dhs.size());
    }

    public void setOnCardClickListener(OnClickCardListener onCardClickListener) {
        this.onCardClickListener = onCardClickListener;
    }

    @LayoutRes
    protected abstract int getItemLayout();

    protected abstract VH getViewHolder(View view);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getItemLayout(), parent, false);
        RxView.clicks(view).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(aVoid ->{
            if (onCardClickListener != null) {
                onCardClickListener.onClickCard((int) view.getTag());
            }
        });
        view.setTag(viewType);
        return getViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindData(listDH.get(position));
    }

    @Override
    public int getItemCount() {
        return listDH.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public DH getItem(int position) {
        return listDH.get(position);
    }

    public ArrayList<DH> getData() { return listDH; }

    public void clear() {
        listDH.clear();
    }

    public void removeItem(int pos) {
        listDH.remove(pos);
        notifyItemRemoved(pos);
    }
}
