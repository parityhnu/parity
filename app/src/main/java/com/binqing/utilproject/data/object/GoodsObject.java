package com.binqing.utilproject.data.object;

import java.io.Serializable;
import java.util.List;

public class GoodsObject implements  Serializable{
    private String mName;

    private String mPrice;

    private String mHref;

    private String mImage;

    private String mKeyword;

    private int mPage;

    private String mShop;

    private int mScore;

    private List<GoodsObject> mParityObjects;

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

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public List<GoodsObject> getParityObjects() {
        return mParityObjects;
    }

    public void setParityObjects(List<GoodsObject> parityObjects) {
        mParityObjects = parityObjects;
    }

    public int getSaleOrComment() {
        if (this instanceof TBObject) {
            String sale = ((TBObject) this).getSale();
            return Integer.parseInt(sale.substring(0, sale.length()-3));
        } else if (this instanceof JDObject) {
            return Integer.parseInt(((JDObject) this).getRootComment());
        }
        return 0;
    }
}
