package com.binqing.utilproject.biz.presenter;

import android.os.Looper;

import com.binqing.utilproject.Activity.ForgetPasswordActivity;
import com.binqing.utilproject.MainLoopHandler;
import com.binqing.utilproject.Utils.LogUtils;
import com.binqing.utilproject.biz.contract.ForgetPasswordContract;

public class ForgetPasswordPresenter implements ForgetPasswordContract.Presenter {

    private ForgetPasswordActivity mView;

    public ForgetPasswordPresenter(ForgetPasswordActivity activity) {
        bindView(activity);
    }

    private void bindView(ForgetPasswordActivity activity) {
        mView = activity;
        MainLoopHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("[TestPresenter]" ,String.valueOf(Looper.getMainLooper().getThread() == Thread.currentThread()));
            }
        });
    }


}
