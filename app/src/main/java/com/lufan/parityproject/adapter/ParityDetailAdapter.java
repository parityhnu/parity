package com.lufan.parityproject.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.lufan.parityproject.R;
import com.lufan.parityproject.Utils.NavUtil;
import com.lufan.parityproject.adapter.base.BaseRecyclerViewAdapter;
import com.lufan.parityproject.adapter.base.BaseViewHolder;
import com.lufan.parityproject.adapter.base.MultiTypeLayout;
import com.lufan.parityproject.data.object.AttOrCommentOrParityObject;
import com.lufan.parityproject.data.object.AttributeObject;
import com.lufan.parityproject.data.object.BaseCommentObject;
import com.lufan.parityproject.data.object.JDCommentObject;
import com.lufan.parityproject.data.object.ParityObject;
import com.lufan.parityproject.data.object.TBCommentObject;
import com.lufan.parityproject.data.object.TMCommentObject;
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
            //对于这个数据来源来说，下面两条语句分别代表参数与评论，二者只能取一个存在
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

                ParityObject parityObject = attributeObject.getParityObject();
                //图片加载
                if (parityObject == null) {
                    holder.setViewVisivility(R.id.iv_attribute1, View.GONE);
                } else {
                    holder.setViewVisivility(R.id.iv_attribute1, View.VISIBLE);
                    ImageView imageView = holder.getView(R.id.iv_attribute1);
                    final AttributeObject finalAttributeObject = attributeObject;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NavUtil.Nav2Web(mContext, "https:" + finalAttributeObject.getParityObject().getHref());
                        }
                    });
                    Glide.with(mContext).load("https:" + parityObject.getImage()).into(imageView);
                }
                String attribute = attributeObject.getAttribute();
                //根据冒号分割
                String[] strings = splitAttibute(attribute);

                holder.setText(R.id.tv_attribute_name, strings[0]);
                if (strings.length >= 2) {
                    holder.setText(R.id.tv_attribute1, strings[1]);
                }

                if (size == 1) {
                    AttributeObject attributeObject1 = new AttributeObject();
                    attributeObject1.setAttribute("");
                    attributeObjectList.add(attributeObject1);
                }
                holder.setViewVisivility(R.id.rl_attribute2, View.VISIBLE);
                attributeObject = attributeObjectList.get(1);
                parityObject = attributeObject.getParityObject();
                if (parityObject == null) {
                    holder.setViewVisivility(R.id.iv_attribute2, View.GONE);
                } else {
                    holder.setViewVisivility(R.id.iv_attribute2, View.VISIBLE);
                    ImageView imageView = holder.getView(R.id.iv_attribute2);
                    final AttributeObject finalAttributeObject1 = attributeObject;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NavUtil.Nav2WebViewActivity(mContext, finalAttributeObject1.getParityObject().getHref());
                        }
                    });
                    Glide.with(mContext).load("https:" + parityObject.getImage()).into(imageView);
                }
                attribute = attributeObject.getAttribute();
                strings = splitAttibute(attribute);
                if (strings.length >= 2) {
                    holder.setText(R.id.tv_attribute2, strings[1]);
                }

            } else if (baseCommentObject != null) {
                //将时间戳转为中文格式的时间
                long time = baseCommentObject.getCtime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                Date date = new Date(time);
                String resultTime = simpleDateFormat.format(date);

                //设置评论

                holder.setText(R.id.tv_ratecontent, baseCommentObject.getRateContent());

                //评论中图片列表的适配器，用的是网格布局，3列
                CommentPicAdapter commentPicAdapter = new CommentPicAdapter(mContext, baseCommentObject.getPics());
                holder.setLayoutManager(R.id.rv_pics, new GridLayoutManager(mContext, 3));
                holder.setRecyclerViewAdapter(R.id.rv_pics, commentPicAdapter);

                //京东没有追加评论的图片，而淘宝和天猫都有
                if (baseCommentObject instanceof JDCommentObject) {
                    holder.setText(R.id.tv_user, baseCommentObject.getDisplayUserNick() + "[京东用户]");
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
                    holder.setText(R.id.tv_user, baseCommentObject.getDisplayUserNick() + "[天猫用户]");
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
                    holder.setText(R.id.tv_user, baseCommentObject.getDisplayUserNick() + "[淘宝用户]");
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
