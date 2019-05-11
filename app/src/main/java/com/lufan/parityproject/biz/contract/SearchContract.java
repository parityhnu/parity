package com.lufan.parityproject.biz.contract;

import java.util.List;

public class SearchContract {
    public interface View {
        void updateSearchEdit(String hint);

        void refreshRecyclerView();
    }

    public interface Presenter{
        void search(String goodsName);

        List<String> getHistory();

        void clearHistory();
    }
}
