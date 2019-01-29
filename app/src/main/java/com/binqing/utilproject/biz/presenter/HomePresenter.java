package com.binqing.utilproject.biz.presenter;

import com.binqing.utilproject.Activity.HomeActivity;
import com.binqing.utilproject.Utils.NavUtil;
import com.binqing.utilproject.biz.contract.HomeContract;

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
