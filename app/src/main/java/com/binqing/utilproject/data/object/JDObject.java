package com.binqing.utilproject.data.object;

import com.binqing.utilproject.data.model.JDModel;

import java.util.ArrayList;
import java.util.List;

public class JDObject extends GoodsObject{

    private String mComment;

    public String getRootComment() {
        return mComment;
    }

    public String getComment() {
        double comment = Integer.parseInt(mComment);
        if (comment>= 10000) {
            comment /= 10000;
            return String.valueOf(comment) + "万";
        } else {
            return mComment;
        }
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public static JDObject fromModel(JDModel model) {
        if (model != null) {
            JDObject object = new JDObject();
            object.setComment(model.comment);
            object.setName(model.name);
            object.setPrice(model.price);
            object.setHref(model.href);
            object.setImage(model.image);
            object.setKeyword(model.keyword);
            object.setPage(model.page);
            object.setShop(model.shop);
            object.setScore(model.score);
            return object;
        }
        return null;
    }

    public static List<JDObject> fromModels(List<JDModel> modelList) {
        List<JDObject> result = new ArrayList<>();
        for (JDModel model : modelList) {
            if (model != null) {
                JDObject object = new JDObject();
                object.setComment(model.comment);
                object.setName(model.name);
                object.setPrice(model.price);
                object.setHref(model.href);
                object.setImage(model.image);
                object.setKeyword(model.keyword);
                object.setPage(model.page);
                object.setShop(model.shop);
                object.setScore(model.score);
                result.add(object);
            }
        }
        return result;
    }
}
