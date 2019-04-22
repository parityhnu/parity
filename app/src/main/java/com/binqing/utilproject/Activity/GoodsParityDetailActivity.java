package com.binqing.utilproject.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.IconFont.IconFontTextView;
import com.binqing.utilproject.R;
import com.binqing.utilproject.adapter.ParityDetailAdapter;
import com.binqing.utilproject.adapter.base.BaseViewHolder;
import com.binqing.utilproject.biz.contract.GoodsParityDetailContract;
import com.binqing.utilproject.biz.presenter.GoodsParityDetailPresenter;
import com.binqing.utilproject.biz.test.TestContract;
import com.binqing.utilproject.biz.test.TestPresenter;
import com.binqing.utilproject.data.object.AttOrCommentOrParityObject;
import com.binqing.utilproject.data.object.AttributeObject;
import com.binqing.utilproject.data.object.BaseCommentObject;
import com.binqing.utilproject.data.object.ParityObject;

import java.util.ArrayList;
import java.util.List;

public class GoodsParityDetailActivity extends BaseActivity implements GoodsParityDetailContract.View {

    private GoodsParityDetailPresenter mPresenter;

    private ImageView mIvBack;
    private RecyclerView mRecyclerView;
    private ParityDetailAdapter mParityDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parity_detail);
        initView();
        initRecyclerView();
        initPresenter();
        initListener();
    }

    private void initPresenter() {
        mPresenter = new GoodsParityDetailPresenter(this);
    }

    private void initView() {
        mIvBack = findViewById(R.id.iv_back);
        mRecyclerView = findViewById(R.id.recyclerview);
    }

    private void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initRecyclerView() {
        List<AttOrCommentOrParityObject> dataList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mParityDetailAdapter = new ParityDetailAdapter(this, dataList, new BaseViewHolder.onItemBaseClickListener() {
            @Override
            public void onItemClickListener(int position) {

            }

            @Override
            public void onItemLongClickListener(int position) {

            }
        });
        mRecyclerView.setAdapter(mParityDetailAdapter);
    }

    @Override
    public void refreshView(List<AttOrCommentOrParityObject> attOrCommentOrParityObjects) {

    }

    @Override
    public List<AttOrCommentOrParityObject> getDataList() {
        if (mParityDetailAdapter != null) {
            return mParityDetailAdapter.getDataList();
        }
        return null;
    }

}
