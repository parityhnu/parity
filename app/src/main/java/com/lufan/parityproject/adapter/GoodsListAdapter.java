package com.lufan.parityproject.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.lufan.parityproject.Enum.GoodsType;
import com.lufan.parityproject.R;
import com.lufan.parityproject.Utils.NavUtil;
import com.lufan.parityproject.adapter.base.BaseRecyclerViewAdapter;
import com.lufan.parityproject.adapter.base.BaseViewHolder;
import com.lufan.parityproject.adapter.base.MultiTypeLayout;
import com.lufan.parityproject.data.object.GoodsObject;
import com.lufan.parityproject.data.object.ParityObject;
import com.bumptech.glide.Glide;

import java.util.List;

public class GoodsListAdapter extends BaseRecyclerViewAdapter<GoodsObject> implements MultiTypeLayout<GoodsObject> {

    private BaseViewHolder.onItemBaseClickListener mOnItemBaseClickListener;
    private Context mContext;

    public GoodsListAdapter(Context context, List dataList) {
        super(context, dataList, R.layout.item_goods);
        this.mContext = context;
        this.mMultiTypeLayout = this;
    }

    public GoodsListAdapter(Context context, List dataList, BaseViewHolder.onItemBaseClickListener onItemBaseClickListener) {
        super(context, dataList, R.layout.item_goods);
        this.mMultiTypeLayout = this;
        this.mContext = context;
        this.mOnItemBaseClickListener = onItemBaseClickListener;
    }

    @Override
    public void bindData(BaseViewHolder holder, GoodsObject Data) {
        if (Data != null) {
            if (Data.getKeyword() == null || "".equals(Data.getKeyword())) {
                return;
            }
            if (Data.getParityObjects() == null || Data.getParityObjects().isEmpty()) {
                holder.setText(R.id.tv_gname, Data.getName());
                holder.setText(R.id.tv_gprcie, Data.getPrice());
                holder.setText(R.id.tv_gname, Data.getName());
                ImageView imageView = holder.getView(R.id.iv_goods_image);
                Glide.with(mContext).load("https:" + Data.getImage()).into(imageView);
                if (Data.getType() == GoodsType.TB.getValue()) {
                    holder.setViewVisivility(R.id.iv_ic_tb, View.VISIBLE);
                    holder.setViewVisivility(R.id.iv_ic_jd, View.GONE);
                    holder.setViewVisivility(R.id.iv_ic_tm, View.GONE);
                    holder.setText(R.id.tv_gcomment_sale, String.valueOf(Data.getSalecomment()) + "人付款");
                } else if (Data.getType() == GoodsType.JD.getValue()) {
                    holder.setViewVisivility(R.id.iv_ic_tb, View.GONE);
                    holder.setViewVisivility(R.id.iv_ic_jd, View.VISIBLE);
                    holder.setViewVisivility(R.id.iv_ic_tm, View.GONE);
                    holder.setText(R.id.tv_gcomment_sale, String.valueOf(Data.getSalecomment()) + "+评论");
                } else {
                    holder.setViewVisivility(R.id.iv_ic_tb, View.GONE);
                    holder.setViewVisivility(R.id.iv_ic_jd, View.GONE);
                    holder.setViewVisivility(R.id.iv_ic_tm, View.VISIBLE);
                    holder.setText(R.id.tv_gcomment_sale, String.valueOf(Data.getSalecomment()) + "人付款");
                }
                holder.setText(R.id.tv_gshop, Data.getShop());
            } else {
                final ParityListAdapter mParityListAdapter = new ParityListAdapter(mContext, Data.getParityObjects());
                mParityListAdapter.setOnItemBaseClickListener( new BaseViewHolder.onItemBaseClickListener() {
                    @Override
                    public void onItemClickListener(int position) {
                        List<ParityObject> parityObjectList = mParityListAdapter.getItemData(position);
                        NavUtil.Nav2GoodsParityDetailActivity(mContext, parityObjectList);
                    }

                    @Override
                    public void onItemLongClickListener(int position) {

                    }
                });

                holder.setLayoutManager(R.id.rv_parity,
                        new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                holder.setRecyclerViewAdapter(R.id.rv_parity, mParityListAdapter);
            }
        }
        holder.setOnItemBaseClickListener(mOnItemBaseClickListener);
    }

    @Override
    public int getLayoutId(GoodsObject item, int postion) {
        if (item.getKeyword() == null || "".equals(item.getKeyword())) {
            return R.layout.item_goods_load_more;
        }
        if (item.getParityObjects() != null && !item.getParityObjects().isEmpty()) {
            return R.layout.list_parity;
        }
        return R.layout.item_goods;
    }
}
