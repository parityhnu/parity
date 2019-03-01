package com.binqing.utilproject.biz.contract;

import com.binqing.utilproject.data.object.GoodsListObject;

import java.util.List;

public class GoodsListContract {
    public interface View {
        void refreshList(GoodsListObject goodsListObject);

        void updateSearchEdit(String hint);
    }

    public interface Presenter{
        void search(String goodsName);

        void requsetGoods(String page);
    }
}
