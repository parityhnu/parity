package com.binqing.utilproject.Activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.Enum.ModifyType;
import com.binqing.utilproject.ParityApplication;
import com.binqing.utilproject.R;
import com.binqing.utilproject.biz.contract.ModifyDetailContract;
import com.binqing.utilproject.biz.presenter.ModifyDetailPresenter;

public class ModifyDetailActivity extends BaseActivity implements ModifyDetailContract.View {

    private ImageView mIvBack;
    private ImageView mIvClearEt1;
    private ImageView mIvClearEt2;
    private ImageView mIvClearCheckPwd;
    private EditText mEt1;
    private EditText mEt2;
    private EditText mEtCheckPwd;
    private Button mBtAchieve;
    private TextView mTvForgetPw;
    private TextView mTvTitle;
    private TextView mTvTip;
    private RelativeLayout mRl2;
    private RelativeLayout mRlCheckPwd;
    private View mDivider;

    private ModifyDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_detail);
        initView();
        initPresenter();
        initListener();
        initData();
    }

    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mIvBack = findViewById(R.id.iv_back);
        mIvClearEt1 = findViewById(R.id.iv_clear_et1);
        mIvClearEt2 = findViewById(R.id.iv_clear_et2);
        mIvClearCheckPwd = findViewById(R.id.iv_clear_password_check);
        mEt1 = findViewById(R.id.et_et1);
        mEt2 = findViewById(R.id.et_et2);
        mEtCheckPwd = findViewById(R.id.et_password_check);
        mBtAchieve = findViewById(R.id.bt_achieve);
        mTvForgetPw = findViewById(R.id.tv_forget_password);
        mTvTip = findViewById(R.id.tv_modify_tip);
        mRl2 = findViewById(R.id.rl_et2);
        mDivider = findViewById(R.id.passwod_divider);
        mRlCheckPwd = findViewById(R.id.rl_password_check);
    }

    private void initData() {
        mPresenter.initData();
    }

    private void initPresenter() {
        mPresenter = new ModifyDetailPresenter(this);
    }

    private void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mIvClearEt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt2.setText("");
            }
        });

        mIvClearEt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt1.setText("");
            }
        });

        mEt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateButtonColor();
                if (mEt1.getText().length() > 0) {
                    mIvClearEt1.setVisibility(View.VISIBLE);
                } else {
                    mIvClearEt1.setVisibility(View.INVISIBLE);
                }
            }
        });

        mEt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateButtonColor();
                if (mEt2.getText().length() > 0) {
                    mIvClearEt2.setVisibility(View.VISIBLE);
                } else {
                    mIvClearEt2.setVisibility(View.INVISIBLE);
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

        mBtAchieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.modify(mEt1.getText().toString(), mEt2.getText().toString(), mEtCheckPwd.getText().toString());
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
        switch (mPresenter.getModifyTyoe()) {
            case NAME:
                if (mEt1.getText().length() > 0) {
                    mBtAchieve.setTextColor(getResources().getColor(R.color.color_white));
                    mBtAchieve.setBackgroundColor(getResources().getColor(R.color.color_theme));
                } else {
                    mBtAchieve.setTextColor(getResources().getColor(R.color.color_unsign_text));
                    mBtAchieve.setBackgroundColor(getResources().getColor(R.color.color_unSign));
                }
                break;
            case PHONE:
                if (mEt1.getText().length() > 0 && mEt2.getText().length() > 0) {
                    mBtAchieve.setTextColor(getResources().getColor(R.color.color_white));
                    mBtAchieve.setBackgroundColor(getResources().getColor(R.color.color_theme));
                } else {
                    mBtAchieve.setTextColor(getResources().getColor(R.color.color_unsign_text));
                    mBtAchieve.setBackgroundColor(getResources().getColor(R.color.color_unSign));
                }
                break;
            case PASSWORD:
                if (mEt1.getText().length() > 0 && mEt2.getText().length() > 0 && mEtCheckPwd.getText().length() > 0) {
                    mBtAchieve.setTextColor(getResources().getColor(R.color.color_white));
                    mBtAchieve.setBackgroundColor(getResources().getColor(R.color.color_theme));
                } else {
                    mBtAchieve.setTextColor(getResources().getColor(R.color.color_unsign_text));
                    mBtAchieve.setBackgroundColor(getResources().getColor(R.color.color_unSign));
                }
                break;
            default:
                break;
        }


    }

    @Override
    public void refreshView(ModifyType modifyType) {
        switch (modifyType) {
            case NAME:
                mTvTitle.setText(R.string.title_modify_name);
                mEt1.setHint(ParityApplication.getInstance().getAccountName());
                mRl2.setVisibility(View.GONE);
                mRlCheckPwd.setVisibility(View.GONE);
                mDivider.setVisibility(View.GONE);
                mTvTip.setText(R.string.tip_modify_name);
                mTvForgetPw.setVisibility(View.GONE);
                mEt1.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case PHONE:
                mTvTitle.setText(R.string.title_modify_phone);
                mRlCheckPwd.setVisibility(View.GONE);
                mEt1.setHint(R.string.enter_present_phone);
                mEt2.setHint(R.string.enter_new_phone);
                mTvTip.setText(R.string.tip_modify_phone);
                mTvForgetPw.setVisibility(View.GONE);
                mEt1.setInputType(InputType.TYPE_CLASS_NUMBER);
                mEt2.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case PASSWORD:
                mRlCheckPwd.setVisibility(View.VISIBLE);
                mTvTitle.setText(R.string.title_modify_password);
                mEt1.setHint(R.string.enter_present_pwd);
                mEt2.setHint(R.string.enter_new_pwd);
                mTvTip.setText(R.string.tip_modify_password);
                mTvForgetPw.setVisibility(View.VISIBLE);
                break;
            default:
                break;
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
