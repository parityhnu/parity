package com.binqing.utilproject.biz.presenter;

import com.binqing.utilproject.Activity.GoodsParityDetailActivity;
import com.binqing.utilproject.biz.contract.GoodsParityDetailContract;

public class GoodsParityDetailPresenter implements GoodsParityDetailContract.Presenter {

    private GoodsParityDetailActivity mView;

    public GoodsParityDetailPresenter(GoodsParityDetailActivity activity) {
        bindView(activity);
    }

    private void bindView(GoodsParityDetailActivity activity) {
        mView = activity;

    }
}
