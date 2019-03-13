package com.binqing.utilproject.biz.presenter;

import android.os.Looper;

import com.binqing.utilproject.Activity.ModifyActivity;
import com.binqing.utilproject.Activity.SignUpActivity;
import com.binqing.utilproject.MainLoopHandler;
import com.binqing.utilproject.Utils.LogUtils;
import com.binqing.utilproject.biz.contract.ModifyContract;
import com.binqing.utilproject.biz.contract.SignUpContract;

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpActivity mView;

    public SignUpPresenter(SignUpActivity activity) {
        bindView(activity);
    }

    private void bindView(SignUpActivity activity) {
        mView = activity;
        MainLoopHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("[TestPresenter]" ,String.valueOf(Looper.getMainLooper().getThread() == Thread.currentThread()));
            }
        });
    }


}
