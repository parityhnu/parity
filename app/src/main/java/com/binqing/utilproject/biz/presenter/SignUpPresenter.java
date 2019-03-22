package com.binqing.utilproject.biz.presenter;

import android.util.Log;
import android.widget.Toast;

import com.binqing.utilproject.Activity.SignUpActivity;
import com.binqing.utilproject.Callback;
import com.binqing.utilproject.Utils.CheckUtil;
import com.binqing.utilproject.biz.contract.SignUpContract;
import com.binqing.utilproject.data.DataProvider;
import com.binqing.utilproject.data.object.UserObject;

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpActivity mView;

    public SignUpPresenter(SignUpActivity activity) {
        bindView(activity);
    }

    private void bindView(SignUpActivity activity) {
        mView = activity;
    }


    @Override
    public void signUp(final String account, String password, String checkPwd, String phone) {
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
        DataProvider.getInstance().register(account, password, phone, new Callback<UserObject>() {
            @Override
            public void onResult(UserObject result) {
                if (result == null) {
                    mView.alert("账户名已被使用");
                    return;
                }
                if (account.equals(result.getAccount())) {
                    Toast.makeText(mView, "注册成功", Toast.LENGTH_SHORT).show();
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
