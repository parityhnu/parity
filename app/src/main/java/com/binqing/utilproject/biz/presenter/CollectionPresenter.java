package com.binqing.utilproject.biz.presenter;

import android.os.Looper;

import com.binqing.utilproject.Activity.CollectionActivity;
import com.binqing.utilproject.Activity.TestActivity;
import com.binqing.utilproject.MainLoopHandler;
import com.binqing.utilproject.Utils.LogUtils;
import com.binqing.utilproject.biz.contract.CollectionContract;

public class CollectionPresenter implements CollectionContract.Presenter {

    private CollectionActivity mView;

    public CollectionPresenter(CollectionActivity activity) {
        bindView(activity);
    }

    private void bindView(CollectionActivity activity) {
        mView = activity;
        MainLoopHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("[TestPresenter]" ,String.valueOf(Looper.getMainLooper().getThread() == Thread.currentThread()));
            }
        });
    }


}
