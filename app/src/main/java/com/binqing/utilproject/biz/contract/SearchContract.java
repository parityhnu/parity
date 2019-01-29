package com.binqing.utilproject.biz.contract;

import java.util.List;

public class SearchContract {
    public interface View {
        void updateSearchEdit(String hint);

        void refreshRecyclerView();
    }

    public interface Presenter{
        void search(String goodName);

        List<String> getHistory();

        void clearHistory();
    }
}
