package com.binqing.utilproject.data.model;

import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.object.JDCommentObject;

import java.util.List;

public class JDCommentModel {

    @Member(order = 0)
    public String gid;

    @Member(order = 1)
    public int index;

    @Member(order = 2)
    public long ctime;

    @Member(order = 3)
    public String rateContent;

    @Member(order = 4)
    public List<String> pics;

    @Member(order = 5)
    public String displayUserNick;

    @Member(order = 6)
    public String content;

    @Member(order = 7)
    public String mProductSize;

    @Member(order = 8)
    public String mProductColor;

    @Member(order = 9)
    public int mScore;

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
