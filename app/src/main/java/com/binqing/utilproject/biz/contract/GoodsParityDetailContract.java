package com.binqing.utilproject.biz.contract;

import com.binqing.utilproject.data.object.AttOrCommentOrParityObject;
import com.binqing.utilproject.data.object.AttributeObject;
import com.binqing.utilproject.data.object.BaseCommentObject;
import com.binqing.utilproject.data.object.ParityObject;

import java.util.List;

public class GoodsParityDetailContract {
    public interface View {
        void refreshView(List<AttOrCommentOrParityObject> attOrCommentOrParityObjects);

        List<AttOrCommentOrParityObject> getDataList();

        void initFavoriteState();

        void updateFavoriteState(boolean hasFavorited);

        void alert(boolean hasFavorited);
    }

    public interface Presenter{
        void requestComment();

        List<ParityObject> getParity();

        void favorite(ParityObject parityObject);

        boolean getFavoriteState(String gid);
    }
}
