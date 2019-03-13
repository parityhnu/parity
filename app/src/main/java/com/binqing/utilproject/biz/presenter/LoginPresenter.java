package com.binqing.utilproject.biz.presenter;

import android.os.Looper;

import com.binqing.utilproject.Activity.LoginActivity;
import com.binqing.utilproject.MainLoopHandler;
import com.binqing.utilproject.Utils.LogUtils;
import com.binqing.utilproject.biz.contract.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginActivity mView;

    public LoginPresenter(LoginActivity activity) {
        bindView(activity);
    }

    private void bindView(LoginActivity activity) {
        mView = activity;
        MainLoopHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("[TestPresenter]" ,String.valueOf(Looper.getMainLooper().getThread() == Thread.currentThread()));
            }
        });
    }


}
