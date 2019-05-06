package com.binqing.utilproject.data.model;


import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.object.TBCommentObject;

import java.util.List;

public class TBCommentModel {

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
    public String auctionSku;

    @Member(order = 8)
    public List<String> attendpics;

    public TBCommentObject toObject() {
        TBCommentObject object = new TBCommentObject();
        object.setContent(content);
        object.setAttendpics(attendpics);
        object.setAuctionSku(auctionSku);
        object.setCtime(ctime);
        object.setRateContent(rateContent);
        object.setDisplayUserNick(displayUserNick);
        object.setGid(gid);
        object.setIndex(index);
        object.setPics(pics);
        return object;
    }
}
