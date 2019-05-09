package com.binqing.utilproject.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.binqing.utilproject.Enum.GoodsType;
import com.binqing.utilproject.R;
import com.binqing.utilproject.Utils.NavUtil;
import com.binqing.utilproject.adapter.base.BaseRecyclerViewAdapter;
import com.binqing.utilproject.adapter.base.BaseViewHolder;
import com.binqing.utilproject.adapter.base.MultiTypeLayout;
import com.binqing.utilproject.data.object.GoodsObject;
import com.binqing.utilproject.data.object.ParityObject;
import com.bumptech.glide.Glide;

import java.util.List;

public class ParityObjectListAdapter extends BaseRecyclerViewAdapter<ParityObject> {

    private BaseViewHolder.onItemBaseClickListener mOnItemBaseClickListener;
    private Context mContext;

    public ParityObjectListAdapter(Context context, List dataList) {
        super(context, dataList, R.layout.item_goods);
        this.mContext = context;
    }

    public ParityObjectListAdapter(Context context, List dataList, BaseViewHolder.onItemBaseClickListener onItemBaseClickListener) {
        super(context, dataList, R.layout.item_goods);
        this.mContext = context;
        this.mOnItemBaseClickListener = onItemBaseClickListener;
    }

    @Override
    public void bindData(BaseViewHolder holder, ParityObject Data) {
        if (Data != null) {
            if (Data.getKeyword() == null || "".equals(Data.getKeyword())) {
                return;
            }
            holder.setText(R.id.tv_gname, Data.getName());
            holder.setText(R.id.tv_gprcie, Data.getPrice());
            holder.setText(R.id.tv_gname, Data.getName());
            ImageView imageView = holder.getView(R.id.iv_goods_image);
            Glide.with(mContext).load("https:" + Data.getImage()).into(imageView);
            if (Data.getType() == GoodsType.TB.getValue()) {
                holder.setViewVisivility(R.id.iv_ic_tb, View.VISIBLE);
                holder.setViewVisivility(R.id.iv_ic_jd, View.GONE);
                holder.setText(R.id.tv_gcomment_sale, String.valueOf(Data.getSalecomment()) + "人付款");
            } else if (Data.getType() == GoodsType.JD.getValue()) {
                holder.setViewVisivility(R.id.iv_ic_tb, View.GONE);
                holder.setViewVisivility(R.id.iv_ic_jd, View.VISIBLE);
                holder.setText(R.id.tv_gcomment_sale, String.valueOf(Data.getSalecomment()) + "+评论");
            }
            holder.setText(R.id.tv_gshop, Data.getShop());
        }
        holder.setOnItemBaseClickListener(mOnItemBaseClickListener);
    }

}
