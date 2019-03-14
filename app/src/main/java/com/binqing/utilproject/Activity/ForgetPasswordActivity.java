package com.binqing.utilproject.Activity;

import android.os.Bundle;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.R;
import com.binqing.utilproject.biz.contract.ForgetPasswordContract;
import com.binqing.utilproject.biz.presenter.ForgetPasswordPresenter;

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordContract.View {

    private ForgetPasswordPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        initPresenter();
        initListener();
    }

    private void initPresenter() {
        mPresenter = new ForgetPasswordPresenter(this);
    }

    private void initView() {

    }

    private void initListener() {

    }
}
