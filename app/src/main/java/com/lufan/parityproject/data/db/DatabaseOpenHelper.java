package com.lufan.parityproject.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        dropDb(db);
    }

    private void dropDb(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT s_name FROM sqlite_master", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                db.execSQL("DROP TABLE " + cursor.getString(0));
                Log.e("[DatabaseOpenHelper]", "删除表 " + cursor.getString(0));
            }
        }
        if (cursor != null) {
            cursor.close();
            cursor = null;
        }
    }

}
