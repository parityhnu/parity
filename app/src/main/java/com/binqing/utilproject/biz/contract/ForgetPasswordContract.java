package com.binqing.utilproject.biz.contract;

public class ForgetPasswordContract {
    public interface View {
        void alert(String tip);
    }

    public interface Presenter{
        void forgetPwd(String account, String phone, String password, String checkPwd);
    }
}
