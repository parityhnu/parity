package com.binqing.utilproject.biz.presenter;

import android.widget.Toast;

import com.binqing.utilproject.Activity.LoginActivity;
import com.binqing.utilproject.Callback;
import com.binqing.utilproject.ParityApplication;
import com.binqing.utilproject.Utils.NavUtil;
import com.binqing.utilproject.biz.contract.LoginContract;
import com.binqing.utilproject.data.DataProvider;
import com.binqing.utilproject.data.object.UserObject;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginActivity mView;

    public LoginPresenter(LoginActivity activity) {
        bindView(activity);
    }

    private void bindView(LoginActivity activity) {
        mView = activity;
    }

    @Override
    public void login(String account, String password) {
        DataProvider.getInstance().login(account, password, new Callback<UserObject>() {
            @Override
            public void onResult(UserObject object) {
                if (object == null) {
                    //密码错误
                    return;
                }
                int user = object.getUid();
                switch (user) {
                    case -1:
                        //密码错误
                    case -2:
                        //错误次数过多
                        mView.alert(user);
                        break;
                    default:
                        ParityApplication.getInstance().setUserId(user);
                        ParityApplication.getInstance().setAccountName(object.getUname());
                        ParityApplication.getInstance().setPhone(object.getPhone());
                        Toast.makeText(mView, "登录成功", Toast.LENGTH_SHORT).show();
                        mView.finish();
                }
            }

            @Override
            public void onException(String code, String reason) {

            }
        });
    }

    @Override
    public void signUp() {
        NavUtil.Nav2SignUpActivity(mView);
    }

    @Override
    public void forgetPassword() {
        NavUtil.Nav2ForgetPasswordActivity(mView);
    }


}
