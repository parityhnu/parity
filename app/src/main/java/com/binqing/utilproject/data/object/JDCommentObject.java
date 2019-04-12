package com.binqing.utilproject.data.object;


import com.binqing.utilproject.data.model.JDCommentModel;

import java.util.ArrayList;
import java.util.List;

public class JDCommentObject extends BaseCommentObject {
    private String mProductSize;

    private String mProductColor;

    private int mScore;

    public String getProductSize() {
        return mProductSize;
    }

    public void setProductSize(String productSize) {
        mProductSize = productSize;
    }

    public String getProductColor() {
        return mProductColor;
    }

    public void setProductColor(String productColor) {
        mProductColor = productColor;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public static List<JDCommentObject> fromModels(List<JDCommentModel> modelList) {
        if (modelList == null || modelList.isEmpty()) {
            return null;
        }
        List<JDCommentObject> result = new ArrayList<>();
        for (JDCommentModel jdCommentModel : modelList) {
            if (jdCommentModel == null) {
                continue;
            }
            result.add(jdCommentModel.toObject());
        }
        return result;
    }

}
