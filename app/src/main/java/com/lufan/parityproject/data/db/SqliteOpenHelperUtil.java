package com.lufan.parityproject.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lufan.parityproject.Callback;
import com.lufan.parityproject.Consts.Consts;
import com.lufan.parityproject.Utils.LogUtils;
import com.lufan.parityproject.data.entry.interfaceEntry.AbsEntry;
import com.lufan.parityproject.data.model.ColumnInfo;
import com.lufan.parityproject.data.model.DBValues;
import com.lufan.parityproject.data.model.DataType;
import com.lufan.parityproject.data.model.TableInfo;
import com.lufan.parityproject.data.parse.AnnoParse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SqliteOpenHelperUtil {

    private static SQLiteOpenHelper mSQLiteOpenHelper;

    private static void makesureObject(Context context, String databaseName, int version) {
        if (mSQLiteOpenHelper == null) {
            mSQLiteOpenHelper = DatabaseOpenHelper.getInstance(context, databaseName, null, version);
        }
    }

    private static boolean createSqliteMasterTable() {
        StringBuilder create_sql = new StringBuilder();
        create_sql.append("CREATE TABLE IF NOT EXISTS ");
        create_sql.append(Consts.DATABASE_SQLITE_MASTER_TABLE_NAME);
        create_sql.append(" ( ");
        create_sql.append(Consts.DATABASE_SQLITE_MASTER_Column_NAME);
        create_sql.append(" varchar(64) PRIMARY KEY ); ");
        SQLiteDatabase database = null;
        try {
            database = mSQLiteOpenHelper.getWritableDatabase();
            database.execSQL(create_sql.toString());
        } catch (RuntimeException e) {
            Log.e("[SqliteOpenHelperUtil]", String.valueOf(e));
            return false;
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return true;
    }

    private static boolean createTable(TableInfo tableInfo) {
        if (tableInfo == null) {
            return false;
        }
        if (!createSqliteMasterTable()) {
            return false;
        }
        SQLiteDatabase database = mSQLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Consts.DATABASE_SQLITE_MASTER_Column_NAME, tableInfo.tableName);
        database.insert(Consts.DATABASE_SQLITE_MASTER_TABLE_NAME, null, contentValues);

        StringBuilder sql = new StringBuilder();
        String tableName = tableInfo.tableName;
        Set<Map.Entry<String, ColumnInfo>> set = tableInfo.colunmMap.entrySet();

        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(tableName);
        sql.append(" ( ");
        sql.append(Consts.DATABASE_PRIMARY_KEY_NAME);
        sql.append(" INTEGER PRIMARY KEY , ");
        for (Map.Entry<String, ColumnInfo> entry : set) {
            ColumnInfo columnInfo = entry.getValue();
            if (columnInfo == null) {
                continue;
            }
            sql.append(" ");
            sql.append(columnInfo.fieldName);
            sql.append(" ");
            sql.append(columnInfo.dbtype.getSqlType());

            if (!DataType.INTEGER.getSqlType().equals(columnInfo.dbtype.getSqlType())) {
                if (columnInfo.columLength <= 0) {
                    return false;
                }
                sql.append("(");
                sql.append(columnInfo.columLength);
                sql.append(")");
            }
            sql.append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(");");
        LogUtils.e("[SqliteOpenHelperUtil] sql: ", sql.toString());
        database.execSQL(sql.toString());
        database.close();
        return true;
    }

    public static void insert(Context context, AbsEntry entry, List<Object> data) {
        if (context == null || entry == null || data == null || data.isEmpty()) {
            return;
        }
        makesureObject(context, Consts.DATABASE_NAME, Consts.DATABASE_VERSION);
        if (!(createTable(AnnoParse.initTableInfo(entry.getClass())))) {
            return;
        }
        List<AbsEntry> entryList = new ArrayList<>();
        for (Object object : data) {
            if (object == null) {
                continue;
            }
            entry.fromObject(object);
            entryList.add(entry);
        }
        DBValues dbValues = AnnoParse.getDBValues(entryList);
        String tableName = dbValues.getTableName();
        List<ContentValues> contentValuesList = dbValues.getContentValuesList();
        if (tableName == null || contentValuesList == null || contentValuesList.isEmpty()) {
            return;
        }
        SQLiteDatabase database = null;
        try {
            database = mSQLiteOpenHelper.getWritableDatabase();
            for (ContentValues contentValues : contentValuesList) {
                database.insert(tableName, null, contentValues);
            }
        } catch (RuntimeException e) {
            LogUtils.e(e.toString());
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public static void delete(Context context, AbsEntry entry, String whereClause, String[] whereArgs) {
        if (context == null || entry == null) {
            return;
        }
        makesureObject(context, Consts.DATABASE_NAME, Consts.DATABASE_VERSION);
        TableInfo tableInfo = AnnoParse.initTableInfo(entry.getClass());
        if (tableInfo == null) {
            return;
        }
        if (!(createTable(tableInfo))) {
            return;
        }
        SQLiteDatabase database = null;
        try {
            database = mSQLiteOpenHelper.getWritableDatabase();
            database.delete(tableInfo.tableName, whereClause, whereArgs);
        } catch (RuntimeException e) {
            LogUtils.e(e.toString());
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public static <T>void rawQuery(Context context, String sql, String[] selectionArgs, OnPackageDataCallback<T> callback, Callback callback1) {
        if (context == null) {
            return;
        }
        makesureObject(context, Consts.DATABASE_NAME, Consts.DATABASE_VERSION);
        SQLiteDatabase database = null;
        Cursor cursor = null;
        List<T> list = new ArrayList<>();
        try {
            database = mSQLiteOpenHelper.getReadableDatabase();
            cursor = database.rawQuery(sql, selectionArgs);
            list = callback.onPackage(cursor);
        } catch (RuntimeException e) {
            LogUtils.e(e.toString());
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        callback1.onResult(list);
    }

    public static <T>void query(Context context, AbsEntry entry,
                                   String whereClause, String[] whereArgs, OnPackageDataCallback<T> callback, Callback callback1) {
        query(context, entry, null, whereClause, whereArgs, null, null, null, callback, callback1);
    }

    public static <T>void query(Context context, AbsEntry entry, String[] columns,
                                   String whereClause, String[] whereArgs,
                                   String groupBy, String having, String orderBy,
                                   OnPackageDataCallback<T> callback, Callback callback1) {
        if (context == null || entry == null || callback == null) {
            callback1.onException("-1", "NPE");
        }
        makesureObject(context, Consts.DATABASE_NAME, Consts.DATABASE_VERSION);
        TableInfo tableInfo = AnnoParse.initTableInfo(entry.getClass());
        if (tableInfo == null) {
            callback1.onException("-1", "NPE");
        }
        if (!(createTable(tableInfo))) {
            callback1.onException("-1", "NPE");
        }
        if (columns == null) {
            int index = 0;
            columns = new String[tableInfo.colunmMap.size()];
            Set<Map.Entry<String, ColumnInfo>> set = tableInfo.colunmMap.entrySet();
            for(Map.Entry<String, ColumnInfo> entry1 : set) {
                String columnName = entry1.getKey();
                columns[index] = columnName;
                index ++;
            }
        }
        SQLiteDatabase database = null;
        Cursor cursor = null;
        List<T> list = new ArrayList<>();
        try {
            database = mSQLiteOpenHelper.getReadableDatabase();
            cursor = database.query(tableInfo.tableName, columns, whereClause, whereArgs, groupBy, having, orderBy);
            list = callback.onPackage(cursor);
        } catch (RuntimeException e) {
            LogUtils.e(e.toString());
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        callback1.onResult(list);
    }

    public interface OnPackageDataCallback<T> {
        List<T> onPackage(Cursor cursor);
    }
}
