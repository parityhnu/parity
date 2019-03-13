package com.binqing.utilproject.Activity;

import android.os.Bundle;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.R;
import com.binqing.utilproject.biz.contract.ModifyContract;
import com.binqing.utilproject.biz.presenter.ModifyPresenter;

public class ModifyActivity extends BaseActivity implements ModifyContract.View {

    private ModifyPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        initPresenter();
        initListener();
    }

    private void initPresenter() {
        mPresenter = new ModifyPresenter(this);
    }

    private void initView() {

    }

    private void initListener() {

    }
}
