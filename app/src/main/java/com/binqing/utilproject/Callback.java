package com.binqing.utilproject;

public interface Callback<T> {
    void onResult(T object);
    void onException(String code, String reason);
}
