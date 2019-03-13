package com.binqing.utilproject.Activity;

import android.os.Bundle;
import android.view.View;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.IconFont.IconFontTextView;
import com.binqing.utilproject.R;
import com.binqing.utilproject.biz.contract.GoodsParityDetailContract;
import com.binqing.utilproject.biz.presenter.GoodsParityDetailPresenter;
import com.binqing.utilproject.biz.test.TestContract;
import com.binqing.utilproject.biz.test.TestPresenter;

public class GoodsParityDetailActivity extends BaseActivity implements GoodsParityDetailContract.View {

    private GoodsParityDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parity_detail);
        initView();
        initPresenter();
        initListener();
    }

    private void initPresenter() {
        mPresenter = new GoodsParityDetailPresenter(this);
    }

    private void initView() {

    }

    private void initListener() {
    }
}
