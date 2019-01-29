package com.binqing.utilproject.data.model;

import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.object.SearchObject;

public class SearchModel {

    @Member(order = 0)
    public String goodname;

    @Member(order = 1)
    public String page;

    public SearchObject fromModel() {
        SearchObject object = new SearchObject();
        object.setPage(page);
        object.setGoodName(goodname);
        return object;
    }

}
