package com.binqing.utilproject.data.model;

import com.binqing.utilproject.data.annotation.Member;

public class JDModel{
    @Member(order = 0)
    public String name;

    @Member(order = 1)
    public String price;

    @Member(order = 2)
    public String href;

    @Member(order = 3)
    public String image;

    @Member(order = 4)
    public String keyword;

    @Member(order = 5)
    public int page;

    @Member(order = 6)
    public String shop;

    @Member(order = 7)
    public int score;

    @Member(order = 8)
    public String comment;


}
