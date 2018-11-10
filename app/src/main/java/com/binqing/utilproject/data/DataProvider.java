package com.binqing.utilproject.data;

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
