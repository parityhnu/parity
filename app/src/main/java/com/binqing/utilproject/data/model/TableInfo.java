package com.binqing.utilproject.data.model;

import android.text.TextUtils;

import java.util.HashMap;

public class TableInfo {
    public String clazzName;
    public String tableName;
    public HashMap<String, ColumnInfo> colunmMap;//数据库的列名与列信息的映射

    public ColumnInfo getColumnByColunmName(String colunmName) {
        if (colunmMap == null || TextUtils.isEmpty(colunmName)) {
            return null;
        }
        return colunmMap.get(colunmName);
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "clazzName='" + clazzName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", colunmMap=" + colunmMap +
                '}';
    }
}
