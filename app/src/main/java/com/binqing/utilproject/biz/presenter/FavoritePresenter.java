package com.binqing.utilproject.biz.presenter;

import com.binqing.utilproject.Activity.FavoriteActivity;
import com.binqing.utilproject.Callback;
import com.binqing.utilproject.Utils.LogUtils;
import com.binqing.utilproject.biz.contract.FavoriteContract;
import com.binqing.utilproject.data.DataProvider;
import com.binqing.utilproject.data.object.ParityObject;

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
