package com.lufan.parityproject.Activity;

import android.os.Bundle;

import com.lufan.parityproject.Activity.base.BaseActivity;
import com.lufan.parityproject.R;
import com.lufan.parityproject.biz.test.TestContract;
import com.lufan.parityproject.biz.test.TestPresenter;

public class TestActivity extends BaseActivity implements TestContract.View {

    private TestPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        initPresenter();
        initListener();
    }

    private void initPresenter() {
        mPresenter = new TestPresenter(this);
    }

    private void initView() {

    }

    private void initListener() {

    }
}
