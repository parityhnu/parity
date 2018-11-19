package com.binqing.utilproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.binqing.utilproject.IconFont.IconFontTextView;
import com.binqing.utilproject.R;
import com.binqing.utilproject.biz.test.MainContract;
import com.binqing.utilproject.biz.test.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private IconFontTextView mIcftTest;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPresenter();
        initListener();
    }

    private void initPresenter() {
        mPresenter = new MainPresenter(this);
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
