package com.binqing.utilproject.biz.presenter;

import com.binqing.utilproject.Activity.ModifyActivity;
import com.binqing.utilproject.Enum.ModifyType;
import com.binqing.utilproject.ParityApplication;
import com.binqing.utilproject.Utils.NavUtil;
import com.binqing.utilproject.biz.contract.ModifyContract;

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
