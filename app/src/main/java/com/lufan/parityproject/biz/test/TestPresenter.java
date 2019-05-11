package com.lufan.parityproject.biz.test;

import android.os.Looper;

import com.lufan.parityproject.Activity.TestActivity;
import com.lufan.parityproject.MainLoopHandler;
import com.lufan.parityproject.Utils.LogUtils;

public class TestPresenter implements TestContract.Presenter {

    private TestActivity mView;

    public TestPresenter(TestActivity activity) {
        bindView(activity);
    }

    private void bindView(TestActivity activity) {
        mView = activity;
        MainLoopHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("[TestPresenter]" ,String.valueOf(Looper.getMainLooper().getThread() == Thread.currentThread()));
            }
        });
    }


}
