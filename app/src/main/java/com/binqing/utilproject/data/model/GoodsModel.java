package com.binqing.utilproject.data.model;

import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.object.GoodsObject;

public class GoodsModel {
    @Member(order = 0)
    public String name;

    @Member(order = 1)
    public String price;

    @Member(order = 2)
    public int salecomment;

    @Member(order = 3)
    public String href;

    @Member(order = 4)
    public String image;

    @Member(order = 5)
    public String keyword;

    @Member(order = 6)
    public int page;

    @Member(order = 7)
    public String shop;

    @Member(order = 8)
    public int sort;

    @Member(order = 9)
    public double score;

    @Member(order = 10)
    public int type;

    public GoodsObject toObject() {
        GoodsObject goodsObject = new GoodsObject();
        goodsObject.setName(name);
        goodsObject.setHref(href);
        goodsObject.setImage(image);
        goodsObject.setKeyword(keyword);
        goodsObject.setPage(page);
        goodsObject.setPrice(price);
        goodsObject.setSalecomment(salecomment);
        goodsObject.setScore(score);
        goodsObject.setShop(shop);
        goodsObject.setSort(sort);
        goodsObject.setType(type);
        return goodsObject;
    }

}
