package com.binqing.utilproject.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.R;
import com.binqing.utilproject.biz.contract.GoodsListContract;
import com.binqing.utilproject.biz.presenter.GoodsListPresenter;

public class GoodsListActivity extends BaseActivity implements GoodsListContract.View {
    
    private GoodsListPresenter mPresenter;
    
    private ImageView mIvBack;
    private TextView mTvSearch;
    private EditText mEtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        initView();
        initPresenter();
        initListener();
        mPresenter.requsetGoods("0");
    }

    private void initPresenter() {
        mPresenter = new GoodsListPresenter(this);
    }

    private void initView() {
        mTvSearch = findViewById(R.id.tv_search);
        mIvBack = findViewById(R.id.iv_back);
        mEtSearch = findViewById(R.id.et_search);
    }

    private void initListener() {
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goodsName = String.valueOf(mEtSearch.getText());
                if ("".equals(goodsName)) {
                    goodsName = String.valueOf(mEtSearch.getHint());
                }
                mPresenter.search(goodsName);
            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void refreshList() {

    }

    @Override
    public void updateSearchEdit(String hint) {
        mEtSearch.setHint(hint);
    }
}
