package com.binqing.utilproject.adapter;

import android.content.Context;

import com.binqing.utilproject.R;
import com.binqing.utilproject.adapter.base.BaseRecyclerViewAdapter;
import com.binqing.utilproject.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodListAdapter extends BaseRecyclerViewAdapter {

    private BaseViewHolder.onItemBaseClickListener mOnItemBaseClickListener;

    public GoodListAdapter(Context context, List dataList) {
        super(context, dataList, R.layout.item_good);
    }

    public GoodListAdapter(Context context, List dataList, BaseViewHolder.onItemBaseClickListener onItemBaseClickListener) {
        super(context, dataList, R.layout.item_good);
        this.mOnItemBaseClickListener = onItemBaseClickListener;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object Data) {

    }
}
