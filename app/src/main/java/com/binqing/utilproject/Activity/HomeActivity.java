package com.binqing.utilproject.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.R;
import com.binqing.utilproject.biz.contract.HomeContract;
import com.binqing.utilproject.biz.presenter.HomePresenter;
import com.binqing.utilproject.fragment.CategoryFragment;
import com.binqing.utilproject.fragment.HomeFragment;
import com.binqing.utilproject.fragment.MineFragment;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    private RelativeLayout mRlTabHome;
    private RelativeLayout mRlTabCategory;
    private RelativeLayout mRlTabMine;

    private Fragment mFmHome;
    private Fragment mFmCategory;
    private Fragment mFmMine;

    private HomePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initPresenter();
        initListener();
        selectTab(TAB_SELECT.home);
    }

    private void initPresenter() {
        mPresenter = new HomePresenter(this);
    }

    private void initView() {
        mRlTabHome = findViewById(R.id.rl_home_tab);
        mRlTabCategory = findViewById(R.id.rl_category_tab);
        mRlTabMine = findViewById(R.id.rl_mine_tab);
    }

    private void initListener() {
        mRlTabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(TAB_SELECT.home);
            }
        });

        mRlTabCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(TAB_SELECT.category);
            }
        });

        mRlTabMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(TAB_SELECT.mine);
            }
        });
    }

    private void selectTab(TAB_SELECT tab_select) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragments(transaction);
        switch (tab_select) {
            case home:
                if (mFmHome == null) {
                    mFmHome = HomeFragment.newInstance(new HomeFragment.OnSearchListener() {
                        @Override
                        public void onSearch() {
                            mPresenter.searchGoods();
                        }
                    });
                    transaction.add(R.id.fl_content, mFmHome);
                } else {
                    transaction.show(mFmHome);
                }
                break;
            case category:
                if (mFmCategory == null) {
                    mFmCategory = CategoryFragment.newInstance();
                    transaction.add(R.id.fl_content, mFmCategory);
                } else {
                    transaction.show(mFmCategory);
                }
                break;
            case mine:
                if (mFmMine == null) {
                    mFmMine = MineFragment.newInstance();
                    transaction.add(R.id.fl_content, mFmMine);
                } else {
                    transaction.show(mFmMine);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mFmHome != null) {
            transaction.hide(mFmHome);
        }
        if (mFmCategory != null) {
            transaction.hide(mFmCategory);
        }
        if (mFmMine != null) {
            transaction.hide(mFmMine);
        }
    }

    private enum TAB_SELECT{
        home,
        category,
        mine
    }
}
