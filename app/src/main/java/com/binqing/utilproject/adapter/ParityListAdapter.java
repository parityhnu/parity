package com.binqing.utilproject.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.binqing.utilproject.Enum.GoodsType;
import com.binqing.utilproject.R;
import com.binqing.utilproject.adapter.base.BaseRecyclerViewAdapter;
import com.binqing.utilproject.adapter.base.BaseViewHolder;
import com.binqing.utilproject.adapter.base.MultiTypeLayout;
import com.binqing.utilproject.data.object.GoodsObject;
import com.binqing.utilproject.data.object.ParityObject;
import com.bumptech.glide.Glide;

import java.util.List;

public class ParityListAdapter extends BaseRecyclerViewAdapter<List<ParityObject>> {

    private BaseViewHolder.onItemBaseClickListener mOnItemBaseClickListener;
    private Context mContext;

    public ParityListAdapter(Context context, List dataList) {
        super(context, dataList, R.layout.item_goods_parity);
        this.mContext = context;
    }

    @Override
    public void bindData(BaseViewHolder holder, List<ParityObject> Data) {
        if (Data != null && !Data.isEmpty()) {
            holder.setText(R.id.tv_gname, Data.get(0).getName());
            holder.setText(R.id.tv_gprcie, Data.get(0).getPrice());
            holder.setText(R.id.tv_gname, Data.get(0).getName());
            ImageView imageView = holder.getView(R.id.iv_goods_image);
            Glide.with(mContext).load("https:" + Data.get(0).getImage()).into(imageView);
            holder.setText(R.id.tv_gparity_count, String.valueOf(Data.size()) + "个比价");
        }
        holder.setOnItemBaseClickListener(mOnItemBaseClickListener);
    }

    public void setOnItemBaseClickListener(BaseViewHolder.onItemBaseClickListener onItemBaseClickListener) {
        mOnItemBaseClickListener = onItemBaseClickListener;
    }
}
