package com.binqing.utilproject.data.object;

import com.binqing.utilproject.data.model.TMCommentModel;

import java.util.ArrayList;
import java.util.List;

public class TMCommentObject extends BaseCommentObject {

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

    public static List<TMCommentObject> fromModels(List<TMCommentModel> modelList) {
        if (modelList == null || modelList.isEmpty()) {
            return null;
        }
        List<TMCommentObject> result = new ArrayList<>();
        for (TMCommentModel model : modelList) {
            if (model == null) {
                continue;
            }
            result.add(model.toObject());
        }
        return result;
    }
}
