package com.binqing.utilproject.Enum;

public enum ModifyType {
    NAME("0"),
    PHONE("1"),
    PASSWORD("2");

    private String mValue;
    ModifyType(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }
}
