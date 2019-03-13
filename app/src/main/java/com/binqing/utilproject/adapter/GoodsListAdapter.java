package com.binqing.utilproject.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.binqing.utilproject.R;
import com.binqing.utilproject.adapter.base.BaseRecyclerViewAdapter;
import com.binqing.utilproject.adapter.base.BaseViewHolder;
import com.binqing.utilproject.adapter.base.MultiTypeLayout;
import com.binqing.utilproject.data.object.GoodsObject;
import com.binqing.utilproject.data.object.JDObject;
import com.binqing.utilproject.data.object.TBObject;
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
            holder.setText(R.id.tv_gname, Data.getName());
            holder.setText(R.id.tv_gprcie, Data.getPrice());
            holder.setText(R.id.tv_gname, Data.getName());
            ImageView imageView = holder.getView(R.id.iv_goods_image);
            Glide.with(mContext).load("https:" + Data.getImage()).into(imageView);
            if (Data.getParityObjects() == null || Data.getParityObjects().isEmpty()) {
                if (Data instanceof TBObject) {
                    holder.setText(R.id.tv_gcomment_sale, ((TBObject) Data).getSale());
                } else if (Data instanceof JDObject) {
                    holder.setText(R.id.tv_gcomment_sale, ((JDObject) Data).getComment() + "+评论");
                }
                holder.setText(R.id.tv_gshop, Data.getShop());
            } else {
                holder.setText(R.id.tv_gparity_count, "有" + Data.getParityObjects().size() + "个商城比价");
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
            return R.layout.item_goods_parity;
        }
        return R.layout.item_goods;
    }
}
