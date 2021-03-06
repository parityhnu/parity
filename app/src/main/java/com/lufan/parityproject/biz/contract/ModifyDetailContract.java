package com.lufan.parityproject.biz.contract;

import com.lufan.parityproject.Enum.ModifyType;

public class ModifyDetailContract {
    public interface View {
        void refreshView(ModifyType modifyType);

        void alert(String tip);
    }

    public interface Presenter{
        void forgetPassword();

        void initData();

        void modify(String s1, String s2, String checkPwd);

        ModifyType getModifyTyoe();
    }
}
