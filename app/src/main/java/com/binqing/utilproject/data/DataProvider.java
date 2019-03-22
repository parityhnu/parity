package com.binqing.utilproject.data;

import com.binqing.utilproject.Callback;
import com.binqing.utilproject.Enum.ModifyType;
import com.binqing.utilproject.data.object.GoodsListObject;
import com.binqing.utilproject.data.object.SearchObject;
import com.binqing.utilproject.data.object.UserObject;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DataProvider {

    private static volatile DataProvider mInstance;

    public static DataProvider getInstance() {
        if (mInstance == null) {
            synchronized (DataProvider.class) {
                if (mInstance == null) {
                    mInstance = new DataProvider();
                }
            }
        }
        return mInstance;
    }

    private Executor mExecutor;
    private ThreadPoolExecutor mThreadPoolExecutor;

    private DataProvider() {
        mExecutor = new Executor();
        mThreadPoolExecutor = getThreadPoolExecutor();
    }

    public void runInExecutor(Runnable command) {
        if (mExecutor == null) {
            return;
        }
        mExecutor.execute(command);
    }

    public void searchGood(final SearchObject searchObject, final Callback<GoodsListObject> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().searchGood(searchObject, callback);
            }
        });
    }

    public void register(final String account, final String password, final String phone, final Callback<UserObject> callback) {
            mExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    DataCenter.getInstance().register(account, password, phone, callback);
                }
            });
    }


    public void login(final String account, final String password, final Callback<UserObject> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().login(account, password, callback);
            }
        });
    }

    public void requestPhone(final Callback<String> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().requestPhone(callback);
            }
        });
    }

    public void modify(final String s1, final String s2, final ModifyType modifyType, final Callback<String> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().modify(s1, s2, modifyType, callback);
            }
        });
    }

    public void forgetPassword(final String account, final String phone, final String passwrod, final Callback<String> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().forgetPassword(account, phone, passwrod, callback);
            }
        });
    }


    private ThreadPoolExecutor getThreadPoolExecutor() {
        return new ThreadPoolExecutor(1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    private class Executor implements java.util.concurrent.Executor {
        @Override
        public void execute( Runnable command) {
            mThreadPoolExecutor.execute(command);
        }
    }
}
