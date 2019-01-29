package com.binqing.utilproject.biz.presenter;

import android.content.Intent;
import android.os.Bundle;

import com.binqing.utilproject.Activity.SearchActivity;
import com.binqing.utilproject.Consts.Consts;
import com.binqing.utilproject.biz.contract.SearchContract;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchActivity mView;

    public SearchPresenter(SearchActivity activity) {
        bindView(activity);
        dealWithIntent();
    }

    private void dealWithIntent() {
        Intent intent = mView.getIntent();
        Bundle bundle = intent.getExtras();
        String hint = bundle.getString(Consts.INTENT_KEY_SEARCH_GOOD_HINT_NAME);
        mView.updateSearchEdit(hint);
    }

    private void bindView(SearchActivity activity) {
        mView = activity;
    }


}
