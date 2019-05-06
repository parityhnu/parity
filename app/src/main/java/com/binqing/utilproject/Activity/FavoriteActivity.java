package com.binqing.utilproject.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.R;
import com.binqing.utilproject.Utils.NavUtil;
import com.binqing.utilproject.adapter.ParityObjectListAdapter;
import com.binqing.utilproject.adapter.base.BaseViewHolder;
import com.binqing.utilproject.biz.contract.FavoriteContract;
import com.binqing.utilproject.biz.presenter.FavoritePresenter;
import com.binqing.utilproject.data.object.ParityObject;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends BaseActivity implements FavoriteContract.View {

    private FavoritePresenter mPresenter;

    private ImageView mIvBack;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private ParityObjectListAdapter mParityObjectListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        initView();
        initPresenter();
        initListener();
        initRecyclerView();
    }

    private void initPresenter() {
        mPresenter = new FavoritePresenter(this);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mProgressBar = findViewById(R.id.progressBar);
        mIvBack = findViewById(R.id.iv_back);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mParityObjectListAdapter = new ParityObjectListAdapter(this, new ArrayList(), new BaseViewHolder.onItemBaseClickListener() {
            @Override
            public void onItemClickListener(int position) {
                ParityObject parityObject = mParityObjectListAdapter.getItemData(position);
                List<ParityObject> parityObjectList = new ArrayList<>();
                parityObjectList.add(parityObject);
                NavUtil.Nav2GoodsParityDetailActivity(getApplication(), parityObjectList);
            }

            @Override
            public void onItemLongClickListener(int position) {

            }
        });
        mRecyclerView.setAdapter(mParityObjectListAdapter);
    }

    @Override
    public void refreshView(List<ParityObject> parityObjects) {
        if (mParityObjectListAdapter == null) {
            initRecyclerView();
        }
        mParityObjectListAdapter.setDataList(parityObjects);
    }

    @Override
    public List<ParityObject> getDataList() {
        if (mParityObjectListAdapter == null) {
            return null;
        }
        return mParityObjectListAdapter.getDataList();
    }

    @Override
    public void onLoad() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadFinish() {
        mProgressBar.setVisibility(View.GONE);
    }
}