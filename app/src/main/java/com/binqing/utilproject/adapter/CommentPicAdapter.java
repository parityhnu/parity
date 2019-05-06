package com.binqing.utilproject.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.binqing.utilproject.R;
import com.binqing.utilproject.adapter.base.BaseRecyclerViewAdapter;
import com.binqing.utilproject.adapter.base.BaseViewHolder;
import com.binqing.utilproject.data.object.ParityObject;
import com.bumptech.glide.Glide;

import java.util.List;

public class CommentPicAdapter extends BaseRecyclerViewAdapter<String> {

    private BaseViewHolder.onItemBaseClickListener mOnItemBaseClickListener;
    private Context mContext;

    public CommentPicAdapter(Context context, List dataList) {
        super(context, dataList, R.layout.item_comment_pic);
        this.mContext = context;
    }

    @Override
    public void bindData(BaseViewHolder holder, String Data) {
        if (Data != null && !TextUtils.isEmpty(Data)) {
            ImageView imageView = holder.getView(R.id.iv_comment_pic);
            Glide.with(mContext).load("https:" + Data).into(imageView);
        }
        holder.setOnItemBaseClickListener(mOnItemBaseClickListener);
    }

    public void setOnItemBaseClickListener(BaseViewHolder.onItemBaseClickListener onItemBaseClickListener) {
        mOnItemBaseClickListener = onItemBaseClickListener;
    }
}
