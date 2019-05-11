package com.lufan.parityproject.data.model;

import com.lufan.parityproject.data.annotation.Member;
import com.lufan.parityproject.data.object.UserObject;

public class UserModel {
    @Member (order = 0)
    public int uid;

    @Member (order = 1)
    public String account;

    @Member (order = 2)
    public String uname;

    @Member (order = 3)
    public String phone;

    public UserObject toObject() {
        UserObject object = new UserObject();
        object.setAccount(account);
        object.setUid(uid);
        object.setUname(uname);
        object.setPhone(phone);
        return object;
    }
}
