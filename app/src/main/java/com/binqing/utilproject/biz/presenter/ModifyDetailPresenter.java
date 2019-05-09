package com.binqing.utilproject.biz.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.binqing.utilproject.Activity.ModifyDetailActivity;
import com.binqing.utilproject.Callback;
import com.binqing.utilproject.Consts.Consts;
import com.binqing.utilproject.Enum.ModifyType;
import com.binqing.utilproject.R;
import com.binqing.utilproject.Utils.CheckUtil;
import com.binqing.utilproject.Utils.NavUtil;
import com.binqing.utilproject.biz.contract.ModifyDetailContract;
import com.binqing.utilproject.data.DataCenter;
import com.binqing.utilproject.data.DataProvider;

public class ModifyDetailPresenter implements ModifyDetailContract.Presenter {

    private ModifyDetailActivity mView;
    private ModifyType mModifyType;

    public ModifyDetailPresenter(ModifyDetailActivity activity) {
        bindView(activity);
    }

    private void bindView(ModifyDetailActivity activity) {
        mView = activity;
    }

    @Override
    public void forgetPassword() {
        NavUtil.Nav2ForgetPasswordActivity(mView);
    }

    @Override
    public void initData() {
        Intent intent = mView.getIntent();
        ModifyType modifyType = (ModifyType) intent.getSerializableExtra(Consts.INTENT_KEY_MODIFY_TYPE);
        mModifyType = modifyType;
        mView.refreshView(mModifyType);
    }


    @Override
    public void modify(final String s1, final String s2, String checkPwd) {
        Callback<String> callback = new Callback<String>() {
            @Override
            public void onResult(String result) {
                if (result == null || "".equals(result)) {
                    return;
                }
                if (mModifyType == ModifyType.NAME) {
                    if (result.equals(s1)) {
                        Toast.makeText(mView, "设置成功",Toast.LENGTH_SHORT).show();
                        mView.finish();
                    }
                } else {
                    if (result.equals(s2)) {
                        Toast.makeText(mView, "设置成功",Toast.LENGTH_SHORT).show();
                        mView.finish();
                    } else {
                        Toast.makeText(mView, "设置失败，请检查是否输入有误",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onException(String code, String reason) {
                Log.e(code,reason);
                Toast.makeText(mView, reason, Toast.LENGTH_SHORT).show();
            }
        };
        switch (mModifyType) {
            case NAME:
                if (s1 == null || "".equals(s1)) {
                    return;
                }
                if (CheckUtil.checkName(s1)) {
                    DataProvider.getInstance().modify(s1, s2, mModifyType, callback);
                } else {
                    mView.alert(String.valueOf(mView.getText(R.string.phone_illegal)));
                }
                break;
            case PASSWORD:
                if (s1 == null || "".equals(s1) || s2 == null || "".equals(s2) || checkPwd == null || "".equals(checkPwd)) {
                    return;
                }
                if (!s2.equals(checkPwd)) {
                    mView.alert("两次密码不相同");
                    return;
                }
                if (CheckUtil.checkPassword(s1) && CheckUtil.checkPassword(s2)) {
                    DataProvider.getInstance().modify(s1, s2, mModifyType, callback);
                } else {
                    mView.alert(String.valueOf(mView.getText(R.string.password_illegal)));
                }
                break;
            case PHONE:
                if (s1 == null || "".equals(s1) || s2 == null || "".equals(s2)) {
                    return;
                }
                if (CheckUtil.checkPhone(s1) && CheckUtil.checkPhone(s2)) {
                    DataProvider.getInstance().modify(s1, s2, mModifyType, callback);
                } else {
                    mView.alert(String.valueOf(mView.getText(R.string.phone_illegal)));
                }
                break;
            default:
                break;
        }
    }
    //ctrl+左键 方法！！！
    @Override
    public ModifyType getModifyTyoe() {
        return mModifyType;
    }


}
