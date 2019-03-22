package com.binqing.utilproject.biz.contract;

public class ModifyContract {
    public interface View {
    }

    public interface Presenter{
        void signOut();

        void modifyPhone();

        void modifyName();

        void modifyPassword();
    }
}
