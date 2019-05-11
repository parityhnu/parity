package com.lufan.parityproject.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.lufan.parityproject.R;
import com.lufan.parityproject.adapter.base.BaseRecyclerViewAdapter;
import com.lufan.parityproject.adapter.base.BaseViewHolder;
import com.lufan.parityproject.data.object.ParityObject;
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
