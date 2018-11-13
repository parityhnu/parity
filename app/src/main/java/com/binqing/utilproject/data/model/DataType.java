package com.binqing.utilproject.data.model;

public enum DataType {
    INTEGER("int"),
    LONG("int"),
    STRING("varchar"),
    BOOLEAN("varchar"),
    SHORT("varchar"),
    FLOAT("varchar"),
    DOUBLE("varchar"),
    ENUM("varchar"),
    UNKOWN("varchar");

    String sqlType;

    DataType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getSqlType() {
        return this.sqlType;
    }
}
