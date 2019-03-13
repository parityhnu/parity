package com.binqing.utilproject;

import android.app.Application;

import com.binqing.utilproject.Consts.Consts;
import com.binqing.utilproject.Utils.PreferenceUtil;

public class ParityApplication extends Application {
    private static ParityApplication mInstance;
    private int mUserId;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        getUser();
    }

    public boolean isSignIn() {
        return mUserId != 0;
    }

    private void getUser() {
        mUserId = PreferenceUtil.getInt(this, Consts.PREDERENCE_USERID);
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
