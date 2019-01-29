package com.binqing.utilproject.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected LayoutInflater mLayoutInflater;

    protected List<T> mDataList;

    protected int mLayoutId;

    protected MultiTypeLayout<T> mMultiTypeLayout;

    public BaseRecyclerViewAdapter(Context context, List<T> dataList, int layoutId) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mDataList = dataList;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiTypeLayout != null) {
            return mMultiTypeLayout.getLayoutId(mDataList.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mMultiTypeLayout != null) {
            mLayoutId = viewType;
        }
        View itemView = mLayoutInflater.inflate(mLayoutId, parent, false);
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder, mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    protected abstract void bindData(BaseViewHolder holder, T data);

}