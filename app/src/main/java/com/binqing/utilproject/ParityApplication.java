package com.binqing.utilproject;

import android.app.Application;

import com.binqing.utilproject.Consts.Consts;
import com.binqing.utilproject.Utils.PreferenceUtil;

public class ParityApplication extends Application {
    private static ParityApplication mInstance;
    private int mUserId;
    private String mAccountName;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        getUser();
        getAccount();
    }

    public boolean isSignIn() {
        return mUserId != 0;
    }

    private void getUser() {
        //退出的时候要清一下
        mUserId = PreferenceUtil.getInt(this, Consts.PREDERENCE_USERID);
    }

    private void getAccount() {
        mAccountName = PreferenceUtil.getUserString(this, Consts.PREDERENCE_ACCOUNT_NAME);
    }

    public static ParityApplication getInstance() {
        return mInstance;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
        PreferenceUtil.setInt(this, Consts.PREDERENCE_USERID, mUserId);
    }

    public String getAccountName() {
        return mAccountName;
    }

    public void setAccountName(String accountName) {
        mAccountName = accountName;
        PreferenceUtil.setUserString(this, Consts.PREDERENCE_ACCOUNT_NAME, mAccountName);
    }
}
