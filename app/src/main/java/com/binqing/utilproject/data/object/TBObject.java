package com.binqing.utilproject.data.object;

import com.binqing.utilproject.data.model.TBModel;

import java.util.ArrayList;
import java.util.List;

public class TBObject{
    private String mName;

    private String mPrice;

    private String mHref;

    private String mImage;

    private String mKeyword;

    private int mPage;

    private String mSale;

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

    public String getSale() {
        return mSale;
    }

    public void setSale(String sale) {
        mSale = sale;
    }

    public static List<TBObject> fromModels(List<TBModel> modelList) {
        List<TBObject> result = new ArrayList<>();
        for (TBModel model : modelList) {
            if (model != null) {
                TBObject object = new TBObject();
                object.setSale(model.sale);
                object.setName(model.name);
                object.setPrice(model.price);
                object.setHref(model.href);
                object.setImage(model.image);
                object.setKeyword(model.keyword);
                object.setPage(model.page);
                result.add(object);
            }
        }
        return result;
    }
}
