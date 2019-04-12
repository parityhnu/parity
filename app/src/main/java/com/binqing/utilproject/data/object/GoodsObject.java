package com.binqing.utilproject.data.object;

import com.binqing.utilproject.data.model.GoodsModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoodsObject implements  Serializable{
    private String mName;

    private String mPrice;

    private int mSalecomment;

    private String mHref;

    private String mImage;

    private String mKeyword;

    private int mPage;

    private String mShop;

    private int mSort;

    private double mScore;

    private int mType;

    private String mId;

    private List<List<ParityObject>> mParityObjects;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getHref() {
        return mHref;
    }

    public void setHref(String href) {
        mHref = href;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getKeyword() {
        return mKeyword;
    }

    public void setKeyword(String keyword) {
        mKeyword = keyword;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public String getShop() {
        if (mShop != null && mShop.length() >= 15) {
            mShop = mShop.substring(0,15);
            mShop += "...";
        }
        return mShop;
    }

    public void setShop(String shop) {
        mShop = shop;
    }

    public int getSalecomment() {
        return mSalecomment;
    }

    public void setSalecomment(int salecomment) {
        mSalecomment = salecomment;
    }

    public int getSort() {
        return mSort;
    }

    public void setSort(int sort) {
        mSort = sort;
    }

    public double getScore() {
        return mScore;
    }

    public void setScore(double score) {
        mScore = score;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public String getId() {
        if (mType == 0) {
            return "jd:" + mId;
        } else if (mType == 1) {
            return "tb:" + mId;
        } else if (mType == 2) {
            return "tm:" + mId;
        }
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public List<List<ParityObject>> getParityObjects() {
        return mParityObjects;
    }

    public void setParityObjects(List<List<ParityObject>> parityObjects) {
        mParityObjects = parityObjects;
    }

    public static List<GoodsObject> fromModels(List<GoodsModel> models) {
        List<GoodsObject> result = new ArrayList<>();
        for (GoodsModel goodsModel : models) {
            if (goodsModel == null) {
                continue;
            }
            result.add(goodsModel.toObject());
        }
        return result;
    }
}
