package com.lufan.parityproject.biz.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.lufan.parityproject.Activity.SearchActivity;
import com.lufan.parityproject.Consts.Consts;
import com.lufan.parityproject.Utils.NavUtil;
import com.lufan.parityproject.Utils.PreferenceUtil;
import com.lufan.parityproject.biz.contract.SearchContract;
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
        String hint = bundle.getString(Consts.INTENT_KEY_SEARCH_GOODS_HINT_NAME);
        mView.updateSearchEdit(hint);
    }

    private void bindView(SearchActivity activity) {
        mView = activity;
    }


    @Override
    public void search(String goodsName) {
        if ("".equals(goodsName)) {
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
        list.remove(goodsName);
        list.add(0,goodsName);
        history = gson.toJson(list);
        PreferenceUtil.setUserString(mView, Consts.PREDERENCE_SEARCH_HISTORY, history);
        NavUtil.Nav2GoodsListActivity(mView, goodsName);
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
