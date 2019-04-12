package com.binqing.utilproject.data.model;


import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.object.TBCommentObject;

import java.util.List;

public class TBCommentModel {

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
    private String auctionSku;

    @Member(order = 8)
    private List<String> attendpics;

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
