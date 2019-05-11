package com.lufan.parityproject.biz.contract;

import com.lufan.parityproject.data.object.AttOrCommentOrParityObject;
import com.lufan.parityproject.data.object.ParityObject;

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
