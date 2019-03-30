package com.binqing.utilproject.biz.presenter;

import com.binqing.utilproject.Activity.CollectionActivity;
import com.binqing.utilproject.biz.contract.CollectionContract;

public class CollectionPresenter implements CollectionContract.Presenter {

    private CollectionActivity mView;

    public CollectionPresenter(CollectionActivity activity) {
        bindView(activity);
    }

    private void bindView(CollectionActivity activity) {
        mView = activity;
    }


}
