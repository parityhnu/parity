package com.binqing.utilproject;

public interface Callback<T> {
    void onResult(T result);
    void onException(String code, String reason);
}
