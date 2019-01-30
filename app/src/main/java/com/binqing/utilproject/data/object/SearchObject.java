package com.binqing.utilproject.data.object;

public class SearchObject {
    private String mGoodsName;

    private String mPage;

    public String getGoodsName() {
        return mGoodsName;
    }

    public void setGoodsName(String goodsName) {
        mGoodsName = goodsName;
    }

    public String getPage() {
        return mPage;
    }

    public void setPage(String page) {
        mPage = page;
    }

    @Override
    public String toString() {
        return "SearchObject{" +
                "mGoodsName='" + mGoodsName + '\'' +
                ", mPage='" + mPage + '\'' +
                '}';
    }
}
