package com.binqing.utilproject.data.db;

import android.content.Context;

import com.binqing.utilproject.Callback;
import com.binqing.utilproject.data.entry.interfaceEntry.AbsEntry;

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

    public void insert(final Context context, final AbsEntry absEntry, final List<Object> objectList) {
        mWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SqliteOpenHelperUtil.insert(context, absEntry, objectList);
            }
        });
    }

    public void delete(final Context context, final AbsEntry absEntry, final String whereClause, final String[] whereArgs) {
        mWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SqliteOpenHelperUtil.delete(context, absEntry, whereClause, whereArgs);
            }
        });
    }

    // 不考虑提供update()方法，由业务逻辑自己进行delete和insert操作
//    public void update() {
//
//    }

    public <T>void rawQury(final Context context, final String sql, final String[] selectionArgs,
                           final SqliteOpenHelperUtil.OnPackageDataCallback<T> callback, final Callback callback1) {
        mReadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SqliteOpenHelperUtil.rawQuery(context, sql, selectionArgs, callback, callback1);
            }
        });
    }

    public <T>void query(final Context context, final AbsEntry entry,
                         final String whereClause, final String[] whereArgs,
                         final SqliteOpenHelperUtil.OnPackageDataCallback<T> callback, final Callback callback1) {
        mReadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SqliteOpenHelperUtil.query(context, entry, whereClause, whereArgs, callback, callback1);
            }
        });
    }

    public <T>void query(final Context context, final AbsEntry entry, final String[] columns,
                         final String whereClause, final String[] whereArgs,
                         final String groupBy, final String having, final String orderBy,
                         final SqliteOpenHelperUtil.OnPackageDataCallback<T> callback, final Callback callback1) {
        mReadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SqliteOpenHelperUtil.query(context, entry, columns, whereClause, whereArgs, groupBy, having, orderBy, callback, callback1);
            }
        });
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
