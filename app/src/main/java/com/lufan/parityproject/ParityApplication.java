package com.lufan.parityproject;

import android.app.Application;

import com.lufan.parityproject.Consts.Consts;
import com.lufan.parityproject.Utils.PreferenceUtil;

public class ParityApplication extends Application {
    private static ParityApplication mInstance;
    private int mUserId;
    private String mAccountName;
    private String mPhone;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        getUser();
        getAccount();
        getPhoneNumber();
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

    public void getPhoneNumber() {
        mPhone = PreferenceUtil.getUserString(this, Consts.PREDERENCE_PHONE);
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

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        StringBuilder builder = new StringBuilder(phone);
        phone = builder.replace(3, 3+4, "****").toString();
        mPhone = phone;
        PreferenceUtil.setUserString(this, Consts.PREDERENCE_PHONE, mPhone);
    }

}
