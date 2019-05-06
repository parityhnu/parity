package com.binqing.utilproject.data.object;

import com.binqing.utilproject.data.model.ParityModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParityObject implements Serializable{
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

    private int mDistance;

    private int mOrder;

    private String mGid;

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

    public int getSalecomment() {
        return mSalecomment;
    }

    public void setSalecomment(int salecomment) {
        mSalecomment = salecomment;
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
        return mShop;
    }

    public void setShop(String shop) {
        mShop = shop;
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

    public int getDistance() {
        return mDistance;
    }

    public void setDistance(int distance) {
        mDistance = distance;
    }

    public int getOrder() {
        return mOrder;
    }

    public void setOrder(int order) {
        mOrder = order;
    }

    public String getGid() {
        return mGid;
    }

    public String getTypeGid() {
        if (mType == 0) {
            return "jd:" + mGid;
        } else if (mType == 1) {
            return "tb:" + mGid;
        } else if (mType == 2) {
            return "tm:" + mGid;
        }
        return mGid;
    }

    public void setGid(String gid) {
        this.mGid = gid;
    }

    public static List<ParityObject> fromModels(List<ParityModel> models) {
        List<ParityObject> result = new ArrayList<>();
        for (ParityModel parityModel : models) {
            if (parityModel == null) {
                continue;
            }
            result.add(parityModel.toObject());
        }
        return result;
    }


}
