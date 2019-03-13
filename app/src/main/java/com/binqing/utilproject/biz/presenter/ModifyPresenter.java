package com.binqing.utilproject.biz.presenter;

import android.os.Looper;

import com.binqing.utilproject.Activity.ModifyActivity;
import com.binqing.utilproject.MainLoopHandler;
import com.binqing.utilproject.Utils.LogUtils;
import com.binqing.utilproject.biz.contract.ModifyContract;

public class ModifyPresenter implements ModifyContract.Presenter {

    private ModifyActivity mView;

    public ModifyPresenter(ModifyActivity activity) {
        bindView(activity);
    }

    private void bindView(ModifyActivity activity) {
        mView = activity;
        MainLoopHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("[TestPresenter]" ,String.valueOf(Looper.getMainLooper().getThread() == Thread.currentThread()));
            }
        });
    }


}
