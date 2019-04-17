package com.binqing.utilproject.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
                    holder.setText(R.id.tv_gcomment_sale, String.valueOf(Data.getSalecomment()));
                } else if (Data.getType() == GoodsType.JD.getValue()) {
                    holder.setViewVisivility(R.id.iv_ic_tb, View.GONE);
                    holder.setViewVisivility(R.id.iv_ic_jd, View.VISIBLE);
                    holder.setText(R.id.tv_gcomment_sale, String.valueOf(Data.getSalecomment()) + "+评论");
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
