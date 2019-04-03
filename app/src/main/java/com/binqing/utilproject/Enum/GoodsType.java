package com.binqing.utilproject.Enum;

public enum GoodsType {
    JD(0),
    TB(1);

    private int mValue;
    GoodsType(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }

}
