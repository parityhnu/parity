package com.lufan.parityproject.biz.presenter;

import android.util.Log;
import android.widget.Toast;

import com.lufan.parityproject.Activity.ForgetPasswordActivity;
import com.lufan.parityproject.Callback;
import com.lufan.parityproject.Utils.CheckUtil;
import com.lufan.parityproject.biz.contract.ForgetPasswordContract;
import com.lufan.parityproject.data.DataProvider;

public class ForgetPasswordPresenter implements ForgetPasswordContract.Presenter {

    private ForgetPasswordActivity mView;

    public ForgetPasswordPresenter(ForgetPasswordActivity activity) {
        bindView(activity);
    }

    private void bindView(ForgetPasswordActivity activity) {
        mView = activity;
    }


    @Override
    public void forgetPwd(final String account, String phone, String password, String checkPwd) {
        if (password == null || !password.equals(checkPwd)) {
            mView.alert("两次密码不相同");
            return;
        }
        if (!CheckUtil.checkAccount(account)) {
            mView.alert("不合法的账户名");
            return;
        }
        if (!CheckUtil.checkPassword(password)) {
            mView.alert("不合法的密码");
            return;
        }
        if (!CheckUtil.checkPhone(phone)) {
            mView.alert("不合法的手机号");
            return;
        }
        DataProvider.getInstance().forgetPassword(account, password, phone, new Callback<String>() {
            @Override
            public void onResult(String result) {
                if (result == null || "-1".equals(result)) {
                    mView.alert("信息错误");
                    return;
                }
                if (account.equals(result)) {
                    Toast.makeText(mView, "设置成功", Toast.LENGTH_SHORT).show();
                    mView.finish();
                }
            }

            @Override
            public void onException(String code, String reason) {
                Toast.makeText(mView, reason, Toast.LENGTH_SHORT).show();
                Log.e(code, reason);
            }
        });
    }
}
