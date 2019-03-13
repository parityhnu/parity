package com.binqing.utilproject.biz.test;

import android.os.Looper;
import android.util.Log;

import com.binqing.utilproject.Activity.TestActivity;
import com.binqing.utilproject.Callback;
import com.binqing.utilproject.MainLoopHandler;
import com.binqing.utilproject.Utils.LogUtils;
import com.binqing.utilproject.data.DataProvider;
import com.binqing.utilproject.data.entry.EntryTest;
import com.binqing.utilproject.data.entry.interfaceEntry.AbsEntry;
import com.binqing.utilproject.data.object.TestObject;
import com.binqing.utilproject.data.object.UserObject;
import com.binqing.utilproject.data.parse.AnnoParse;

import java.util.ArrayList;
import java.util.List;

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
