package com.binqing.utilproject.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.R;
import com.binqing.utilproject.biz.contract.LoginContract;
import com.binqing.utilproject.biz.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity implements LoginContract.View{


    LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initPresenter();
    }

    private void initPresenter() {
        mPresenter = new LoginPresenter(this);
    }

    private void initView() {

    }

}
