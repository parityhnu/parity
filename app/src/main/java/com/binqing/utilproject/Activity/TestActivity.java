package com.binqing.utilproject.Activity;

import android.os.Bundle;
import android.view.View;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.IconFont.IconFontTextView;
import com.binqing.utilproject.R;
import com.binqing.utilproject.biz.test.TestContract;
import com.binqing.utilproject.biz.test.TestPresenter;

public class TestActivity extends BaseActivity implements TestContract.View {

    private IconFontTextView mIcftTest;
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
        mIcftTest = findViewById(R.id.ictv_test);
    }

    private void initListener() {
        mIcftTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.testAnnotation();
            }
        });
    }
}
