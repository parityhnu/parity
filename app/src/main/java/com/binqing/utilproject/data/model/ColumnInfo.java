package com.binqing.utilproject.data.model;

public class ColumnInfo {

    public String fieldName; //字段名
    public String fieldtype; // 字段类型
    public int columLength; //列的长度
    public DataType dbtype;

    @Override
    public String toString() {
        return "ColumnInfo{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldtype='" + fieldtype + '\'' +
                ", columLength=" + columLength +
                ", dbtype=" + dbtype +
                '}';
    }
}
