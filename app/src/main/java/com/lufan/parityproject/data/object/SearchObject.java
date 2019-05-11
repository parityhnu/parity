package com.lufan.parityproject.data.object;

public class SearchObject {
    private String mGoodsName;

    private String mPage;

    private String mSort;

    public SearchObject(String goodsName, String page, String sort) {
        mGoodsName = goodsName;
        mPage = page;
        mSort = sort;
    }

    public String getGoodsName() {
        return mGoodsName;
    }

    public String getPage() {
        return mPage;
    }

    public String getSort() {
        return mSort;
    }

    @Override
    public String toString() {
        return "SearchObject{" +
                "mGoodsName='" + mGoodsName + '\'' +
                ", mPage='" + mPage + '\'' +
                '}';
    }
}
