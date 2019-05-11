package com.lufan.parityproject.biz.presenter;

import com.lufan.parityproject.Activity.ModifyActivity;
import com.lufan.parityproject.Enum.ModifyType;
import com.lufan.parityproject.ParityApplication;
import com.lufan.parityproject.Utils.NavUtil;
import com.lufan.parityproject.biz.contract.ModifyContract;

public class ModifyPresenter implements ModifyContract.Presenter {

    private ModifyActivity mView;

    public ModifyPresenter(ModifyActivity activity) {
        bindView(activity);
    }

    private void bindView(ModifyActivity activity) {
        mView = activity;
    }


    @Override
    public void signOut() {
        ParityApplication.getInstance().setUserId(0);
        mView.finish();
    }

    @Override
    public void modifyPhone() {
        NavUtil.Nav2ModifyDetailActivity(mView, ModifyType.PHONE);
    }

    @Override
    public void modifyName() {
        NavUtil.Nav2ModifyDetailActivity(mView, ModifyType.NAME);
    }

    @Override
    public void modifyPassword() {
        NavUtil.Nav2ModifyDetailActivity(mView, ModifyType.PASSWORD);
    }
}
