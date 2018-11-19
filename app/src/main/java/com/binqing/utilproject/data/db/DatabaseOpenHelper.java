package com.binqing.utilproject.data.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static volatile DatabaseOpenHelper mInstance;

    public static DatabaseOpenHelper getInstance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        if (mInstance == null) {
            synchronized (DatabaseOpenHelper.class) {
                if (mInstance == null) {
                    mInstance = new DatabaseOpenHelper(context, name, factory, version);
                }
            }
        }
        return mInstance;
    }

    private DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //todo 清一次表

    }

}
