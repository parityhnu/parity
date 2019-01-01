package com.binqing.utilproject.data.model;

import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.object.GoodObject;

public class GoodModel {

    @Member(order = 0)
    public String goodname;

    @Member(order = 1)
    public String page;

    public GoodObject fromModel() {
        GoodObject object = new GoodObject();
        object.setPage(page);
        object.setGoodName(goodname);
        return object;
    }

}
