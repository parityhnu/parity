package com.binqing.utilproject.Activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.R;
import com.binqing.utilproject.Utils.NavUtil;
import com.binqing.utilproject.biz.contract.SearchContract;
import com.binqing.utilproject.biz.presenter.HomePresenter;
import com.binqing.utilproject.biz.presenter.SearchPresenter;
import com.binqing.utilproject.biz.test.TestContract;
import com.binqing.utilproject.fragment.CategoryFragment;
import com.binqing.utilproject.fragment.HomeFragment;
import com.binqing.utilproject.fragment.MineFragment;

public class SearchActivity extends BaseActivity implements SearchContract.View {

    private TextView mEtSearch;

    private SearchPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initPresenter();
        initListener();
    }

    private void initPresenter() {
        mPresenter = new SearchPresenter(this);
    }

    private void initView() {
        mEtSearch = findViewById(R.id.search);
    }

    private void initListener() {
    }

    @Override
    public void updateSearchEdit(String hint) {
        mEtSearch.setHint(hint);
    }
}
