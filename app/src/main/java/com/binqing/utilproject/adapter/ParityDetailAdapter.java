package com.binqing.utilproject.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.binqing.utilproject.R;
import com.binqing.utilproject.adapter.base.BaseRecyclerViewAdapter;
import com.binqing.utilproject.adapter.base.BaseViewHolder;
import com.binqing.utilproject.adapter.base.MultiTypeLayout;
import com.binqing.utilproject.data.object.AttOrCommentOrParityObject;
import com.binqing.utilproject.data.object.AttributeObject;
import com.binqing.utilproject.data.object.BaseCommentObject;
import com.binqing.utilproject.data.object.JDCommentObject;
import com.binqing.utilproject.data.object.ParityObject;
import com.binqing.utilproject.data.object.TBCommentObject;
import com.binqing.utilproject.data.object.TMCommentObject;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ParityDetailAdapter extends BaseRecyclerViewAdapter<AttOrCommentOrParityObject> implements MultiTypeLayout<AttOrCommentOrParityObject> {

    private BaseViewHolder.onItemBaseClickListener mOnItemBaseClickListener;
    private Context mContext;

    public ParityDetailAdapter(Context context, List dataList) {
        super(context, dataList, R.layout.item_parity_attribute);
        this.mContext = context;
        this.mMultiTypeLayout = this;
    }

    public ParityDetailAdapter(Context context, List dataList, BaseViewHolder.onItemBaseClickListener onItemBaseClickListener) {
        super(context, dataList, R.layout.item_parity_attribute);
        this.mMultiTypeLayout = this;
        this.mContext = context;
        this.mOnItemBaseClickListener = onItemBaseClickListener;
    }

    @Override
    public void bindData(BaseViewHolder holder, AttOrCommentOrParityObject Data) {
        if (Data != null) {
            List<AttributeObject> attributeObjectList = Data.getAttributeObjectList();
            BaseCommentObject baseCommentObject = Data.getBaseCommentObject();
            int index = Data.getIndex();
            if (attributeObjectList != null && !attributeObjectList.isEmpty()) {
                //初始化
                holder.setBackGround(R.id.ll_background, index/2 == 0 ? R.color.attribute_background1 : R.color.attribute_background2);
                holder.setText(R.id.tv_attribute1, "");
                holder.setText(R.id.tv_attribute2, "");

                int size = attributeObjectList.size();
                AttributeObject attributeObject = attributeObjectList.get(0);

                //图片加载
                if (attributeObject.getImgUrl() == null || TextUtils.isEmpty(attributeObject.getImgUrl())) {
                    holder.setViewVisivility(R.id.iv_attribute1, View.GONE);
                } else {
                    holder.setViewVisivility(R.id.iv_attribute1, View.VISIBLE);
                    ImageView imageView = holder.getView(R.id.iv_attribute1);
                    Glide.with(mContext).load("https:" + attributeObject.getImgUrl()).into(imageView);
                }
                String attribute = attributeObject.getAttribute();
                String[] strings = splitAttibute(attribute);

                holder.setText(R.id.tv_attribute_name, strings[0]);
                if (strings.length >= 2) {
                    holder.setText(R.id.tv_attribute1, strings[1]);
                }
                if (size >= 2) {
                    holder.setViewVisivility(R.id.rl_attribute2, View.VISIBLE);
                    attributeObject = attributeObjectList.get(1);
                    if (attributeObject.getImgUrl() == null || TextUtils.isEmpty(attributeObject.getImgUrl())) {
                        holder.setViewVisivility(R.id.iv_attribute2, View.GONE);
                    } else {
                        holder.setViewVisivility(R.id.iv_attribute2, View.VISIBLE);
                        ImageView imageView = holder.getView(R.id.iv_attribute2);
                        Glide.with(mContext).load("https:" + attributeObject.getImgUrl()).into(imageView);
                    }
                    attribute = attributeObject.getAttribute();
                    strings = splitAttibute(attribute);
                    if (strings.length >= 2) {
                        holder.setText(R.id.tv_attribute2, strings[1]);
                    }
                } else {
                    holder.setViewVisivility(R.id.rl_attribute2, View.INVISIBLE);
                }
            } else if (baseCommentObject != null) {
                long time = baseCommentObject.getCtime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                Date date = new Date(time);
                String resultTime = simpleDateFormat.format(date);

                holder.setText(R.id.tv_user, baseCommentObject.getDisplayUserNick());
                holder.setText(R.id.tv_ratecontent, baseCommentObject.getRateContent());
                CommentPicAdapter commentPicAdapter = new CommentPicAdapter(mContext, baseCommentObject.getPics());
                holder.setLayoutManager(R.id.rv_pics, new GridLayoutManager(mContext, 3));
                holder.setRecyclerViewAdapter(R.id.rv_pics, commentPicAdapter);

                if (baseCommentObject instanceof JDCommentObject) {
                    holder.setText(R.id.tv_time_sku, resultTime + " "
                            + ((JDCommentObject) baseCommentObject).getProductSize()
                            + ((JDCommentObject) baseCommentObject).getProductColor());

                    if (baseCommentObject.getContent() == null || TextUtils.isEmpty(baseCommentObject.getContent())
                            || "{ }".equals(baseCommentObject.getContent())) {
                        holder.setViewVisivility(R.id.ll_append, View.GONE);
                    } else {
                        holder.setText(R.id.tv_append_content, baseCommentObject.getContent());
                    }
                } else if (baseCommentObject instanceof TMCommentObject) {
                    holder.setText(R.id.tv_time_sku, resultTime + " "
                            + ((TMCommentObject) baseCommentObject).getAuctionSku());
                    if (baseCommentObject.getContent() == null || TextUtils.isEmpty(baseCommentObject.getContent())
                            || "{ }".equals(baseCommentObject.getContent())) {
                        holder.setViewVisivility(R.id.ll_append, View.GONE);
                    } else {
                        holder.setText(R.id.tv_append_content, baseCommentObject.getContent());
                        commentPicAdapter = new CommentPicAdapter(mContext, ((TMCommentObject) baseCommentObject).getAttendpics());
                        holder.setLayoutManager(R.id.rv_append_pics, new GridLayoutManager(mContext, 3));
                        holder.setRecyclerViewAdapter(R.id.rv_append_pics, commentPicAdapter);
                    }
                } else if (baseCommentObject instanceof TBCommentObject) {
                    holder.setText(R.id.tv_time_sku, resultTime + " "
                            + ((TBCommentObject) baseCommentObject).getAuctionSku());
                    if (baseCommentObject.getContent() == null || TextUtils.isEmpty(baseCommentObject.getContent())
                            || "{ }".equals(baseCommentObject.getContent())) {
                        holder.setViewVisivility(R.id.ll_append, View.GONE);
                    } else {
                        holder.setText(R.id.tv_append_content, baseCommentObject.getContent());
                        commentPicAdapter = new CommentPicAdapter(mContext, ((TBCommentObject) baseCommentObject).getAttendpics());
                        holder.setLayoutManager(R.id.rv_append_pics, new GridLayoutManager(mContext, 3));
                        holder.setRecyclerViewAdapter(R.id.rv_append_pics, commentPicAdapter);
                    }
                }
            }
        }
        holder.setOnItemBaseClickListener(mOnItemBaseClickListener);
    }

    private String[] splitAttibute(String attribute) {
        String[] strings = attribute.split(":", 2);
        if (strings.length < 2) {
            strings = attribute.split("：", 2);
        }
        return strings;
    }

    @Override
    public int getLayoutId(AttOrCommentOrParityObject item, int postion) {
        if (item.getAttributeObjectList() != null) {
            return R.layout.item_parity_attribute;
        }
        if (item.getBaseCommentObject() != null) {
            return R.layout.item_comment;
        }
        return R.layout.item_parity_attribute;
    }
}
