package com.binqing.utilproject.data.object;

public class UserObject {
    private Integer mUid;

    private String mAccount;

    private String mUname;

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

    @Override
    public String toString() {
        return "UserObject{" +
                "mUid=" + mUid +
                ", mAccount='" + mAccount + '\'' +
                ", mUname='" + mUname + '\'' +
                '}';
    }
}
