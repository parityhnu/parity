package com.binqing.utilproject.data.db;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DBManager {

    private static volatile DBManager mInstance;
    private ReadExecutor mReadExecutor;
    private WriteExecutor mWriteExecutor;
    private ThreadPoolExecutor mReadThreadPoolExecutor;
    private ThreadPoolExecutor mWriteThreadPoolExecutor;

    public static DBManager getInstance() {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager();
                }
            }
        }
        return mInstance;
    }

    public void insert() {

    }

    public void delete() {}

    public void update() {

    }

    public <T>List<T> query() {
        List<T> list = new ArrayList<>();

        return list;
    }

    private DBManager() {
        mReadExecutor = new ReadExecutor();
        mWriteExecutor = new WriteExecutor();
        mReadThreadPoolExecutor = getReadThreadPoolExecutor();
        mWriteThreadPoolExecutor = getWriteThreadPoolExecutor();
    }

    private ThreadPoolExecutor getReadThreadPoolExecutor() {
        return new ThreadPoolExecutor(4,
                4,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    private class ReadExecutor implements java.util.concurrent.Executor {
        @Override
        public void execute( Runnable command) {
            mReadThreadPoolExecutor.execute(command);
        }
    }

    private ThreadPoolExecutor getWriteThreadPoolExecutor() {
        return new ThreadPoolExecutor(1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    private class WriteExecutor implements java.util.concurrent.Executor {
        @Override
        public void execute( Runnable command) {
            mWriteThreadPoolExecutor.execute(command);
        }
    }
}
