package com.lufan.parityproject.biz.contract;

import com.lufan.parityproject.data.object.ParityObject;

import java.util.List;

public class FavoriteContract {
    public interface View {
        void refreshView(List<ParityObject> parityObjects);

        List<ParityObject> getDataList();

        void onLoad();

        void onLoadFinish();
    }

    public interface Presenter{

    }
}
