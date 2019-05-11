package com.lufan.parityproject.data.object;

import java.util.List;

public class GoodsListObject {

    private int mMaxPage;

    private List<ParityObject> mParityObjectList;

    private List<GoodsObject> mGoodsObjectList;

    public int getMaxPage() {
        return mMaxPage;
    }

    public void setMaxPage(int maxPage) {
        mMaxPage = maxPage;
    }

    public List<ParityObject> getParityObjectList() {
        return mParityObjectList;
    }

    public void setParityObjectList(List<ParityObject> parityObjectList) {
        mParityObjectList = parityObjectList;
    }

    public List<GoodsObject> getGoodsObjectList() {
        return mGoodsObjectList;
    }

    public void setGoodsObjectList(List<GoodsObject> goodsObjectList) {
        mGoodsObjectList = goodsObjectList;
    }
}
