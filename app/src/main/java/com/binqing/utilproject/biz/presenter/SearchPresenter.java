package com.binqing.utilproject.biz.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.binqing.utilproject.Activity.SearchActivity;
import com.binqing.utilproject.Consts.Consts;
import com.binqing.utilproject.Utils.PreferenceUtil;
import com.binqing.utilproject.biz.contract.SearchContract;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchActivity mView;

    public SearchPresenter(SearchActivity activity) {
        bindView(activity);
        dealWithIntent();
    }

    private void dealWithIntent() {
        Intent intent = mView.getIntent();
        Bundle bundle = intent.getExtras();
        String hint = bundle.getString(Consts.INTENT_KEY_SEARCH_GOOD_HINT_NAME);
        mView.updateSearchEdit(hint);
    }

    private void bindView(SearchActivity activity) {
        mView = activity;
    }


    @Override
    public void search(String goodName) {
        if ("".equals(goodName)) {
            return;
        }
        String history = PreferenceUtil.getUserString(mView, Consts.PREDERENCE_SEARCH_HISTORY);
        List<String> list = new ArrayList<>();
        Gson gson = new Gson();
        if (!"".equals(history) && history != null) {
            list = gson.fromJson(history, new TypeToken<List<String>>(){}.getType());
        }
        if (list.size() >= 10) {
            list.remove(9);
        }
        list.remove(goodName);
        list.add(0,goodName);
        history = gson.toJson(list);
        PreferenceUtil.setUserString(mView, Consts.PREDERENCE_SEARCH_HISTORY, history);
    }

    @Override
    public List<String> getHistory() {
        String history = PreferenceUtil.getUserString(mView, Consts.PREDERENCE_SEARCH_HISTORY);
        List<String> list = new ArrayList<>();
        Gson gson = new Gson();
        if (!"".equals(history)) {
            list = gson.fromJson(history, new TypeToken<List<String>>(){}.getType());
        }
        return list;
    }

    @Override
    public void clearHistory() {
        PreferenceUtil.setUserString(mView, Consts.PREDERENCE_SEARCH_HISTORY, "");
        mView.refreshRecyclerView();
    }
}
