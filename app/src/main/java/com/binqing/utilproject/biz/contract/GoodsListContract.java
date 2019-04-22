package com.binqing.utilproject.biz.contract;

import com.binqing.utilproject.Enum.SortType;
import com.binqing.utilproject.data.object.GoodsListObject;
import com.binqing.utilproject.data.object.GoodsObject;
import com.binqing.utilproject.data.object.ParityObject;

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
