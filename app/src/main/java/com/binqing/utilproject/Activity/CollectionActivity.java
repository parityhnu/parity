package com.binqing.utilproject.Activity;

import android.os.Bundle;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.R;
import com.binqing.utilproject.biz.contract.CollectionContract;
import com.binqing.utilproject.biz.presenter.CollectionPresenter;
import com.binqing.utilproject.biz.test.TestPresenter;

public class CollectionActivity extends BaseActivity implements CollectionContract.View {

    private CollectionPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        initPresenter();
        initListener();
    }

    private void initPresenter() {
        mPresenter = new CollectionPresenter(this);
    }

    private void initView() {

    }

    private void initListener() {

    }
}
