package com.binqing.utilproject.biz.contract;

import com.binqing.utilproject.data.object.AttributeObject;
import com.binqing.utilproject.data.object.BaseCommentObject;
import com.binqing.utilproject.data.object.ParityObject;

import java.util.List;

public class GoodsParityDetailContract {
    public interface View {
        void refreshView(List<ParityObject> parityObjectList);

        void refreshComment(List<BaseCommentObject> baseCommentObjects);

        void refreshAttribute(List<AttributeObject> attributeObjects);
    }

    public interface Presenter{
        void requestComment();

        void requestAttribute();
    }
}
