package com.lufan.parityproject.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lufan.parityproject.Activity.base.BaseActivity;
import com.lufan.parityproject.ParityApplication;
import com.lufan.parityproject.R;
import com.lufan.parityproject.biz.contract.ModifyContract;
import com.lufan.parityproject.biz.presenter.ModifyPresenter;

public class ModifyActivity extends BaseActivity implements ModifyContract.View {

    private ModifyPresenter mPresenter;

    private RelativeLayout mRlName;
    private RelativeLayout mRlPhone;
    private RelativeLayout mRlPassword;
    private Button mBtSignOut;
    private TextView mTvName;
    private TextView mTvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        initView();
        initPresenter();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshView();
    }

    private void initPresenter() {
        mPresenter = new ModifyPresenter(this);
    }

    private void initView() {
        mRlName = findViewById(R.id.rl_name);
        mRlPassword = findViewById(R.id.rl_password);
        mRlPhone = findViewById(R.id.rl_phone);
        mBtSignOut = findViewById(R.id.bt_sign_out);
        mTvName = findViewById(R.id.tv_user_name);
        mTvPhone = findViewById(R.id.tv_user_phone);
        refreshView();
    }

    private void initListener() {
        mRlName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.modifyName();
            }
        });

        mRlPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.modifyPhone();
            }
        });

        mRlPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.modifyPassword();
            }
        });

        mBtSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.signOut();
            }
        });
    }

    @Override
    public void refreshView() {
        mTvName.setText(ParityApplication.getInstance().getAccountName());
        mTvPhone.setText(ParityApplication.getInstance().getPhone());
    }
}
