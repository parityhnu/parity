package com.lufan.parityproject;

public interface Callback<T> {
    void onResult(T result);
    void onException(String code, String reason);
}
