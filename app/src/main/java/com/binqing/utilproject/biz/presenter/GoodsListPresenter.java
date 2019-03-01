package com.binqing.utilproject.biz.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.binqing.utilproject.Activity.GoodsListActivity;
import com.binqing.utilproject.Callback;
import com.binqing.utilproject.Consts.Consts;
import com.binqing.utilproject.Utils.PreferenceUtil;
import com.binqing.utilproject.biz.contract.GoodsListContract;
import com.binqing.utilproject.data.DataProvider;
import com.binqing.utilproject.data.object.GoodsListObject;
import com.binqing.utilproject.data.object.SearchObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GoodsListPresenter implements GoodsListContract.Presenter {

    private GoodsListActivity mView;
    private String mGoodsName;

    public GoodsListPresenter(GoodsListActivity activity) {
        bindView(activity);
        dealWithIntent();
    }

    private void dealWithIntent() {
        Intent intent = mView.getIntent();
        Bundle bundle = intent.getExtras();
        String goodsName = bundle.getString(Consts.INTENT_KEY_SEARCH_GOODS_NAME);
        mGoodsName = goodsName;
        mView.updateSearchEdit(mGoodsName);
    }

    private void bindView(GoodsListActivity activity) {
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
        mGoodsName = goodsName;
        requsetGoods("0");
    }

    @Override
    public void requsetGoods(String page) {
        SearchObject object = new SearchObject();
        object.setPage(page);
        object.setGoodsName(mGoodsName);
        //todo 设置网络错误或超时的toast
        DataProvider.getInstance().searchGood(object, new Callback<GoodsListObject>() {
            @Override
            public void onResult(GoodsListObject goodsListObject) {
                mView.refreshList(goodsListObject);
            }

            @Override
            public void onException(String code, String reason) {

            }
        });
    }

}
