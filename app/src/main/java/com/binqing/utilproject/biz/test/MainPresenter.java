package com.binqing.utilproject.biz.test;

import android.os.Looper;

import com.binqing.utilproject.Activity.MainActivity;
import com.binqing.utilproject.MainLoopHandler;
import com.binqing.utilproject.Utils.LogUtils;
import com.binqing.utilproject.data.db.DBManager;
import com.binqing.utilproject.data.entry.EntryTest;
import com.binqing.utilproject.data.entry.interfaceEntry.AbsEntry;
import com.binqing.utilproject.data.object.TestObject;
import com.binqing.utilproject.data.parse.AnnoParse;

import java.util.ArrayList;
import java.util.List;

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
        List<Object> testObjects = new ArrayList<>();
        TestObject testObject = new TestObject();
        testObject.setId(1);
        testObject.setName("1123123123123");
        testObjects.add(testObject);
        AbsEntry entryTest = new EntryTest();
        DBManager.getInstance().insert(mView, entryTest, testObjects);
    }

}
