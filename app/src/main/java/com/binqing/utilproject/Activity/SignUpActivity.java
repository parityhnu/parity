package com.binqing.utilproject.Activity;

import android.os.Bundle;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.R;
import com.binqing.utilproject.biz.contract.SignUpContract;
import com.binqing.utilproject.biz.presenter.SignUpPresenter;

public class SignUpActivity extends BaseActivity implements SignUpContract.View {

    private SignUpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        initPresenter();
        initListener();
    }

    private void initPresenter() {
        mPresenter = new SignUpPresenter(this);
    }

    private void initView() {

    }

    private void initListener() {

    }
}
