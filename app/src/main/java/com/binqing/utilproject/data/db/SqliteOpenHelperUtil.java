package com.binqing.utilproject.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.binqing.utilproject.Consts.Consts;
import com.binqing.utilproject.Utils.LogUtils;
import com.binqing.utilproject.data.entry.interfaceEntry.AbsEntry;
import com.binqing.utilproject.data.model.ColumnInfo;
import com.binqing.utilproject.data.model.DBValues;
import com.binqing.utilproject.data.model.DataType;
import com.binqing.utilproject.data.model.TableInfo;
import com.binqing.utilproject.data.parse.AnnoParse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SqliteOpenHelperUtil {

    private static SQLiteOpenHelper mSQLiteOpenHelper;

    private static void makesureObject(Context context, String databaseName, int version) {
        if (mSQLiteOpenHelper == null) {
            mSQLiteOpenHelper = new DatabaseOpenHelper(context, databaseName, null, version);
        }
    }

    private static boolean createTable(TableInfo tableInfo) {
        if (tableInfo == null) {
            return false;
        }
        StringBuilder sql = new StringBuilder();
        String tableName = tableInfo.tableName;
        Set<Map.Entry<String, ColumnInfo>> set = tableInfo.colunmMap.entrySet();

        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(tableName);
        sql.append(" (");
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
        SQLiteDatabase database = mSQLiteOpenHelper.getWritableDatabase();
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
        SQLiteDatabase database = mSQLiteOpenHelper.getWritableDatabase();
        for (ContentValues contentValues : contentValuesList) {
            database.insert(tableName, null, contentValues);
        }
        database.close();
    }
}
