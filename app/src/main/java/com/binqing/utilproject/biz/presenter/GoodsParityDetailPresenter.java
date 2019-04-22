package com.binqing.utilproject.biz.presenter;

import android.content.Intent;

import com.binqing.utilproject.Activity.GoodsParityDetailActivity;
import com.binqing.utilproject.Consts.Consts;
import com.binqing.utilproject.biz.contract.GoodsParityDetailContract;
import com.binqing.utilproject.data.object.ParityObject;

import java.util.List;

public class GoodsParityDetailPresenter implements GoodsParityDetailContract.Presenter {

    private GoodsParityDetailActivity mView;
    private List<ParityObject> mData;

    public GoodsParityDetailPresenter(GoodsParityDetailActivity activity) {
        bindView(activity);
        initData();
    }

    private void bindView(GoodsParityDetailActivity activity) {
        mView = activity;
    }


    private void initData() {
        Intent intent = mView.getIntent();
        mData = intent.getParcelableExtra(Consts.INTENT_KEY_PARITY_GOODS_DETAIL);
    }

    @Override
    public void requestComment() {

    }

    @Override
    public void requestAttribute() {

    }
}
