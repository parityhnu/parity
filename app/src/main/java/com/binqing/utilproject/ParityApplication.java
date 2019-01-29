package com.binqing.utilproject;

import android.app.Application;

public class ParityApplication extends Application {
    private static ParityApplication mInstance;
    private int mUserId;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mUserId = 0;
    }

    public static ParityApplication getInstance() {
        return mInstance;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

}
