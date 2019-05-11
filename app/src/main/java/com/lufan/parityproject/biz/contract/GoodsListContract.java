package com.lufan.parityproject.biz.contract;

import com.lufan.parityproject.Enum.SortType;
import com.lufan.parityproject.data.object.GoodsObject;

import java.util.List;

public class GoodsListContract {
    public interface View {
        void refreshList(List<GoodsObject> dataList);

        void updateSearchEdit(String hint);

        void onLoad();

        void onLoadFinish();

        List<GoodsObject> getDataList();
    }

    public interface Presenter{
        void search(String goodsName, SortType sort);

        void requsetGoods(String page, SortType sort);

        String getGoodName();

        String getPage();
    }
}
