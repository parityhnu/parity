package com.lufan.parityproject.data.model;

import android.content.ContentValues;

import java.util.List;

public class DBValues {
    private String mTableName;
    private List<ContentValues> mContentValuesList;

    public String getTableName() {
        return mTableName;
    }

    public List<ContentValues> getContentValuesList() {
        return mContentValuesList;
    }

    public DBValues(String tableName, List<ContentValues> contentValuesList) {
        mTableName = tableName;
        mContentValuesList = contentValuesList;
    }
}
