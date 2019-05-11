package com.lufan.parityproject.biz.presenter;

import com.lufan.parityproject.Activity.HomeActivity;
import com.lufan.parityproject.Utils.NavUtil;
import com.lufan.parityproject.biz.contract.HomeContract;

public class HomePresenter implements HomeContract.Presenter {

    private HomeActivity mView;

    public HomePresenter(HomeActivity activity) {
        bindView(activity);
    }

    private void bindView(HomeActivity activity) {
        mView = activity;
    }
    public void searchGoods() {
        NavUtil.Nav2SearchActivity(mView,"hello");
    }

}
