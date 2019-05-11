package com.lufan.parityproject.Enum;

public enum SortType {
    INIT("0"),
    SALE_COMMENT_DESC("1"),
    PRICE_ASC("2"),
    PRICE_DESC("3");

    private String mValue;
    SortType(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }

}
