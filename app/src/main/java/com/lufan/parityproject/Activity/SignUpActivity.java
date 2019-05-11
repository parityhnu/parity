package com.lufan.parityproject.Activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lufan.parityproject.Activity.base.BaseActivity;
import com.lufan.parityproject.R;
import com.lufan.parityproject.biz.contract.SignUpContract;
import com.lufan.parityproject.biz.presenter.SignUpPresenter;

public class SignUpActivity extends BaseActivity implements SignUpContract.View {

    private ImageView mIvBack;
    private ImageView mIvClearAccount;
    private ImageView mIvClearPassword;
    private ImageView mIvClearPhone;
    private ImageView mIvClearCheckPwd;
    private EditText mEtAccount;
    private EditText mEtPassword;
    private EditText mEtPhone;
    private EditText mEtCheckPwd;
    private Button mBtSignUp;

    private SignUpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        initPresenter();
        initListener();
    }

    private void initPresenter() {
        mPresenter = new SignUpPresenter(this);
    }

    private void initView() {
        mIvBack = findViewById(R.id.iv_back);
        mIvClearAccount = findViewById(R.id.iv_clear_account);
        mIvClearPassword = findViewById(R.id.iv_clear_password);
        mIvClearPhone = findViewById(R.id.iv_clear_phone);
        mIvClearCheckPwd = findViewById(R.id.iv_clear_password_check);
        mEtAccount = findViewById(R.id.et_account);
        mEtPassword = findViewById(R.id.et_password);
        mEtPhone = findViewById(R.id.et_phone);
        mEtCheckPwd = findViewById(R.id.et_password_check);
        mBtSignUp = findViewById(R.id.bt_sign_up);

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

        mIvClearCheckPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtCheckPwd.setText("");
            }
        });


        mIvClearPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtPhone.setText("");
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

        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateButtonColor();
                if (mEtPhone.getText().length() > 0) {
                    mIvClearPhone.setVisibility(View.VISIBLE);
                } else {
                    mIvClearPhone.setVisibility(View.INVISIBLE);
                }
            }
        });

        mEtCheckPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateButtonColor();
                if (mEtCheckPwd.getText().length() > 0) {
                    mIvClearCheckPwd.setVisibility(View.VISIBLE);
                } else {
                    mIvClearCheckPwd.setVisibility(View.INVISIBLE);
                }
            }
        });

        mBtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.signUp(mEtAccount.getText().toString(), mEtPassword.getText().toString(),
                        mEtCheckPwd.getText().toString(), mEtPhone.getText().toString());
            }
        });
    }

    private void updateButtonColor() {
        if (mEtAccount.getText().length() > 0 && mEtPassword.getText().length() > 0 &&
                mEtPhone.getText().length() > 0 && mEtCheckPwd.getText().length() > 0) {
            mBtSignUp.setTextColor(getResources().getColor(R.color.color_white));
            mBtSignUp.setBackgroundColor(getResources().getColor(R.color.color_theme));
        } else {
            mBtSignUp.setTextColor(getResources().getColor(R.color.color_unsign_text));
            mBtSignUp.setBackgroundColor(getResources().getColor(R.color.color_unSign));
        }
    }

    @Override
    public void alert(String tip) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(tip);
        builder.show();
    }
}
