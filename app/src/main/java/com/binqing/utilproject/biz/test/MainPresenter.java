package com.binqing.utilproject.biz.test;

import android.os.Looper;

import com.binqing.utilproject.Activity.MainActivity;
import com.binqing.utilproject.MainLoopHandler;
import com.binqing.utilproject.Utils.LogUtils;
import com.binqing.utilproject.data.entry.EntryTest;
import com.binqing.utilproject.data.parse.AnnoParse;

public class MainPresenter implements MainContract.Presenter {

    private MainActivity mView;

    public MainPresenter(MainActivity activity) {
        bindView(activity);
    }

    private void bindView(MainActivity activity) {
        mView = activity;
        MainLoopHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("[MainPresenter]" ,String.valueOf(Looper.getMainLooper().getThread() == Thread.currentThread()));
            }
        });
    }

    public void testAnnotation() {
        LogUtils.e("[MainPresenter]", AnnoParse.initTableInfo(EntryTest.class).toString());
    }

}
