package com.binqing.utilproject.biz.test;

import com.binqing.utilproject.Activity.MainActivity;

public class MainPresenter implements MainContract.Presenter {

    private MainActivity mView;

    public MainPresenter(MainActivity activity) {
        bindView(activity);
    }

    private void bindView(MainActivity activity) {
        mView = activity;
    }

}
