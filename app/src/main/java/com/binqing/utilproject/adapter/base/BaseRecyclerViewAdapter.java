package com.binqing.utilproject.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<T> mDataList;
    private int mLayoutId;
    private MultiTypeLayout<T> mMultiTypeLayout;

    public BaseRecyclerViewAdapter(Context context, List<T> dataList, int layoutId) {
        this.mContext = context;
        this.mDataList = dataList;
        this.mLayoutId = layoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mMultiTypeLayout != null) {
            mLayoutId = viewType;
        }
        View view = mLayoutInflater.inflate(mLayoutId, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder, mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public abstract void bindData(BaseViewHolder holder, T Data);
}
