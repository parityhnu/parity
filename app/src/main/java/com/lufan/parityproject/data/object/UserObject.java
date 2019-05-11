package com.lufan.parityproject.data.object;

public class UserObject {
    private Integer mUid;

    private String mAccount;

    private String mUname;

    private String mPhone;

    public Integer getUid() {
        return mUid;
    }

    public void setUid(Integer uid) {
        mUid = uid;
    }

    public String getAccount() {
        return mAccount;
    }

    public void setAccount(String account) {
        mAccount = account;
    }

    public String getUname() {
        return mUname;
    }

    public void setUname(String uname) {
        mUname = uname;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    @Override
    public String toString() {
        return "UserObject{" +
                "mUid=" + mUid +
                ", mAccount='" + mAccount + '\'' +
                ", mUname='" + mUname + '\'' +
                '}';
    }
}
