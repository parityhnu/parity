package com.lufan.parityproject.biz.presenter;

import com.lufan.parityproject.Activity.FavoriteActivity;
import com.lufan.parityproject.Callback;
import com.lufan.parityproject.Utils.LogUtils;
import com.lufan.parityproject.biz.contract.FavoriteContract;
import com.lufan.parityproject.data.DataProvider;
import com.lufan.parityproject.data.object.ParityObject;

import java.util.List;

public class FavoritePresenter implements FavoriteContract.Presenter {

    private FavoriteActivity mView;

    public FavoritePresenter(FavoriteActivity activity) {
        bindView(activity);
        initData();
    }

    private void bindView(FavoriteActivity activity) {
        mView = activity;
    }

    private void initData() {
        mView.onLoad();
        DataProvider.getInstance().getFavorite(new Callback<List<ParityObject>>() {
            @Override
            public void onResult(List<ParityObject> result) {
                if (result == null) {
                    return;
                }
                mView.onLoadFinish();
                mView.refreshView(result);
            }

            @Override
            public void onException(String code, String reason) {
                mView.onLoadFinish();
                LogUtils.e(code, reason);
            }
        });
    }
}
