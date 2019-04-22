package com.binqing.utilproject.data.model;

import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.object.ParityObject;

public class ParityModel {
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

    @Member(order = 11)
    public int distance;

    @Member(order = 12)
    public int order;

    @Member(order = 13)
    public String gid;

    public ParityObject toObject() {
        ParityObject parityObject = new ParityObject();
        parityObject.setName(name);
        parityObject.setDistance(distance);
        parityObject.setHref(href);
        parityObject.setImage(image);
        parityObject.setKeyword(keyword);
        parityObject.setOrder(order);
        parityObject.setPage(page);
        parityObject.setPrice(price);
        parityObject.setSalecomment(salecomment);
        parityObject.setScore(score);
        parityObject.setShop(shop);
        parityObject.setSort(sort);
        parityObject.setType(type);
        parityObject.setGid(gid);
        return parityObject;
    }
}
