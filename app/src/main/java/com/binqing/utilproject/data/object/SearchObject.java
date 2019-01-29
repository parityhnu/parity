package com.binqing.utilproject.data.object;

public class SearchObject {
    private String mGoodName;

    private String mPage;

    public String getGoodName() {
        return mGoodName;
    }

    public void setGoodName(String goodName) {
        mGoodName = goodName;
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
                "mGoodName='" + mGoodName + '\'' +
                ", mPage='" + mPage + '\'' +
                '}';
    }
}
