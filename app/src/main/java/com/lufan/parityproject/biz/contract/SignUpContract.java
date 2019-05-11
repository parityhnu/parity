package com.lufan.parityproject.biz.contract;

public class SignUpContract {
    public interface View {
        void alert(String tip);
    }

    public interface Presenter{
        void signUp(String account, String password, String checkPwd, String phone);
    }
}
