package com.binqing.utilproject.data.model;

import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.object.GoodsListObject;
import com.binqing.utilproject.data.object.JDObject;
import com.binqing.utilproject.data.object.TBObject;

import java.util.List;

public class GoodsListModel {
    @Member(order = 0)
    public List<JDModel> jdModelList;

    @Member(order = 1)
    public List<TBModel> tbModelList;

    public GoodsListObject toObjct() {
        GoodsListObject goodsListObject = new GoodsListObject();
        goodsListObject.setJDObjectList(JDObject.fromModels(jdModelList));
        goodsListObject.setTBObjectList(TBObject.fromModels(tbModelList));
        return goodsListObject;
    }
}
