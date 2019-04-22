package com.binqing.utilproject.data.object;

import com.binqing.utilproject.data.model.TBCommentModel;

import java.util.ArrayList;
import java.util.List;

public class TBCommentObject extends BaseCommentObject {

    private String mAuctionSku;

    private List<String> mAttendpics;

    public String getAuctionSku() {
        return mAuctionSku;
    }

    public void setAuctionSku(String auctionSku) {
        mAuctionSku = auctionSku;
    }

    public List<String> getAttendpics() {
        return mAttendpics;
    }

    public void setAttendpics(List<String> attendpics) {
        mAttendpics = attendpics;
    }

    public static List<TBCommentObject> fromModels(List<TBCommentModel> modelList) {
        if (modelList == null || modelList.isEmpty()) {
            return null;
        }
        List<TBCommentObject> result = new ArrayList<>();
        for (TBCommentModel model : modelList) {
            if (model == null) {
                continue;
            }
            result.add(model.toObject());
        }
        return result;
    }
}
