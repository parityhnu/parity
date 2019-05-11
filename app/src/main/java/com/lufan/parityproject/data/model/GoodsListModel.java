package com.lufan.parityproject.data.model;

import com.lufan.parityproject.data.annotation.Member;
import com.lufan.parityproject.data.object.GoodsListObject;
import com.lufan.parityproject.data.object.GoodsObject;
import com.lufan.parityproject.data.object.ParityObject;

import java.util.List;

public class GoodsListModel {

    @Member(order = 0)
    public int maxPage;

    @Member(order = 1)
    public List<ParityModel> parityGoodsList;

    @Member(order = 2)
    public List<GoodsModel> goodsModelList;

    public GoodsListObject toObjct() {
        GoodsListObject goodsListObject = new GoodsListObject();
        goodsListObject.setMaxPage(maxPage);
        goodsListObject.setParityObjectList(ParityObject.fromModels(parityGoodsList));
        goodsListObject.setGoodsObjectList(GoodsObject.fromModels(goodsModelList));
        return goodsListObject;
    }
}
