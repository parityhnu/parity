package com.binqing.utilproject.biz.contract;

public class GoodsListContract {
    public interface View {
        void refreshList();

        void updateSearchEdit(String hint);
    }

    public interface Presenter{
        void search(String goodsName);

        void requsetGoods(String page);
    }
}
