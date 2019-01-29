package com.binqing.utilproject.adapter;

import android.content.Context;

import com.binqing.utilproject.R;
import com.binqing.utilproject.adapter.base.BaseRecyclerViewAdapter;
import com.binqing.utilproject.adapter.base.BaseViewHolder;
import com.binqing.utilproject.adapter.base.MultiTypeLayout;

import java.util.List;

public class SearchHistoryListAdapter extends BaseRecyclerViewAdapter<String> implements MultiTypeLayout<String> {

    private BaseViewHolder.onItemBaseClickListener mOnItemBaseClickListener;

    public SearchHistoryListAdapter(Context context, List dataList) {
        super(context, dataList, R.layout.item_search_history);
        this.mMultiTypeLayout = this;
    }

    public SearchHistoryListAdapter(Context context, List dataList, BaseViewHolder.onItemBaseClickListener onItemBaseClickListener) {
        super(context, dataList, R.layout.item_search_history);
        this.mMultiTypeLayout = this;
        this.mOnItemBaseClickListener = onItemBaseClickListener;
    }

    @Override
    public void bindData(BaseViewHolder holder, String Data) {
        if (holder.getAdapterPosition() != getItemCount()-1) {
            holder.setText(R.id.tv_search_history, Data);
        }
        holder.setOnItemBaseClickListener(mOnItemBaseClickListener);
    }

    @Override
    public int getLayoutId(String item, int postion) {
        if (postion == getItemCount()-1) {
            return R.layout.item_clear_history;
        }
        return R.layout.item_search_history;
    }
}
