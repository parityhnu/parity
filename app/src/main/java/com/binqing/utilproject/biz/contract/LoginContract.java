package com.binqing.utilproject.biz.contract;

public class LoginContract {
    public interface View {
    }

    public interface Presenter{
        void login(String account, String password);

        void signUp();

        void forgetPassword();
    }
}
