package com.binqing.utilproject.biz.test;

import android.database.Cursor;
import android.os.Looper;

import com.binqing.utilproject.Activity.MainActivity;
import com.binqing.utilproject.Callback;
import com.binqing.utilproject.MainLoopHandler;
import com.binqing.utilproject.Utils.LogUtils;
import com.binqing.utilproject.data.db.DBManager;
import com.binqing.utilproject.data.db.SqliteOpenHelperUtil;
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
        final TestObject testObject = new TestObject();
        testObject.setId(1);
        testObject.setName("1123123123123");
        testObject.setTestC("111111");
        testObjects.add(testObject);
        AbsEntry entryTest = new EntryTest();
        DBManager.getInstance().insert(mView, entryTest, testObjects);
//        DBManager.getInstance().delete(mView, entryTest, "t_id = ?", new String[]{"2"});
//        SqliteOpenHelperUtil.OnPackageDataCallback<TestObject> callback = new SqliteOpenHelperUtil.OnPackageDataCallback<TestObject>() {
//            @Override
//            public List<TestObject> onPackage(Cursor cursor) {
//                List<TestObject> testObjectList = new ArrayList<>();
//                while (cursor.moveToNext()) {
//                    TestObject testObject1 = new TestObject();
//                    testObject1.setId(cursor.getLong(0));
//                    testObject1.setName(cursor.getString(1));
//                    testObject1.setTestC(cursor.getString(2));
//                    testObjectList.add(testObject1);
//                }
//                return testObjectList;
//            }
//        };
//        Callback<List<TestObject>> callback1 = new Callback<List<TestObject>>() {
//            @Override
//            public void onResult(List<TestObject> object) {
//
//                for (TestObject testObject1 : object) {
//                    LogUtils.e("[MainPresenter]", testObject1.toString());
//                }
//            }
//            @Override
//            public void onException(String code, String reason) {
//
//            }
//        };
//        DBManager.getInstance().query(mView, entryTest, null, null, callback, callback1);
    }

}
