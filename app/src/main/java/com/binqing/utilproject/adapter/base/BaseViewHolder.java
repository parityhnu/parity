package com.binqing.utilproject.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private SparseArray<View> mViewSparseArray;

    private onItemBaseClickListener mOnItemBaseClickListener;

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        mViewSparseArray = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        // 先从缓存中找，找打的话则直接返回
        // 如果找不到则 findViewById ，再把结果存入缓存中
        View view = mViewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int viewId, CharSequence text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    public BaseViewHolder setViewVisivility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    public BaseViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    public BaseViewHolder setLayoutManager(int viewId, RecyclerView.LayoutManager manager) {
        RecyclerView recyclerView = getView(viewId);
        recyclerView.setLayoutManager(manager);
        return this;
    }

    public BaseViewHolder setRecyclerViewAdapter(int viewId, BaseRecyclerViewAdapter adapter) {
        RecyclerView recyclerView = getView(viewId);
        recyclerView.setAdapter(adapter);
        return this;
    }

    public interface onItemBaseClickListener {

        void onItemClickListener(int position);

        void onItemLongClickListener(int position);

    }

    public void setOnItemBaseClickListener(onItemBaseClickListener onItemBaseClickListener) {
        this.mOnItemBaseClickListener = onItemBaseClickListener;
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemBaseClickListener != null) {
            mOnItemBaseClickListener.onItemLongClickListener(getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemBaseClickListener != null) {
            mOnItemBaseClickListener.onItemClickListener(getAdapterPosition());
        }
    }
}
