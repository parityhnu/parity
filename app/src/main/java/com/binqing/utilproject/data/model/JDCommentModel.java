package com.binqing.utilproject.data.model;

import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.object.JDCommentObject;

import java.util.List;

public class JDCommentModel {

    @Member(order = 0)
    private String gid;

    @Member(order = 1)
    private int index;

    @Member(order = 2)
    private long ctime;

    @Member(order = 3)
    private String rateContent;

    @Member(order = 4)
    private List<String> pics;

    @Member(order = 5)
    private String displayUserNick;

    @Member(order = 6)
    private String content;

    @Member(order = 7)
    private String mProductSize;

    @Member(order = 8)
    private String mProductColor;

    @Member(order = 9)
    private int mScore;

    public JDCommentObject toObject() {
        JDCommentObject object = new JDCommentObject();
        object.setContent(content);
        object.setProductColor(mProductColor);
        object.setProductSize(mProductSize);
        object.setScore(mScore);
        object.setCtime(ctime);
        object.setRateContent(rateContent);
        object.setDisplayUserNick(displayUserNick);
        object.setGid(gid);
        object.setIndex(index);
        object.setPics(pics);
        return object;
    }

}
