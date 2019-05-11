package com.lufan.parityproject.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lufan.parityproject.Activity.base.BaseActivity;
import com.lufan.parityproject.R;
import com.lufan.parityproject.biz.contract.LoginContract;
import com.lufan.parityproject.biz.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity implements LoginContract.View{

    private ImageView mIvBack;
    private ImageView mIvClearAccount;
    private ImageView mIvClearPassword;
    private EditText mEtAccount;
    private EditText mEtPassword;
    private Button mBtLogin;
    private TextView mTvForgetPw;
    private TextView mTvSignUp;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initPresenter();
        initListener();
    }

    private void initPresenter() {
        mPresenter = new LoginPresenter(this);
    }

    private void initView() {
        mIvBack = findViewById(R.id.iv_back);
        mIvClearAccount = findViewById(R.id.iv_clear_account);
        mIvClearPassword = findViewById(R.id.iv_clear_password);
        mEtAccount = findViewById(R.id.et_account);
        mEtPassword = findViewById(R.id.et_password);
        mBtLogin = findViewById(R.id.bt_login);
        mTvForgetPw = findViewById(R.id.tv_forget_password);
        mTvSignUp = findViewById(R.id.tv_sign_up);
    }


    private void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mIvClearPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtPassword.setText("");
            }
        });

        mIvClearAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtAccount.setText("");
            }
        });

        mEtAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateButtonColor();
                if (mEtAccount.getText().length() > 0) {
                    mIvClearAccount.setVisibility(View.VISIBLE);
                } else {
                    mIvClearAccount.setVisibility(View.INVISIBLE);
                }
            }
        });


        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateButtonColor();
                if (mEtPassword.getText().length() > 0) {
                    mIvClearPassword.setVisibility(View.VISIBLE);
                } else {
                    mIvClearPassword.setVisibility(View.INVISIBLE);
                }
            }
        });

        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEtAccount.getText().length() > 0 && mEtPassword.getText().length() > 0) {
                    mPresenter.login(mEtAccount.getText().toString(), mEtPassword.getText().toString());
                }
            }
        });

        mTvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.signUp();
            }
        });

        mTvForgetPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.forgetPassword();
            }
        });

    }

    private void updateButtonColor() {
        if (mEtAccount.getText().length() > 0 && mEtPassword.getText().length() > 0) {
            mBtLogin.setTextColor(getResources().getColor(R.color.color_white));
            mBtLogin.setBackgroundColor(getResources().getColor(R.color.color_theme));
        } else {
            mBtLogin.setTextColor(getResources().getColor(R.color.color_unsign_text));
            mBtLogin.setBackgroundColor(getResources().getColor(R.color.color_unSign));
        }
    }

    @Override
    public void alert(int type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(type == -1?"账户名或者密码错误":"错误次数过多，请5分钟后再试");
        builder.show();
    }
}
