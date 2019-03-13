package com.binqing.utilproject.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.R;
import com.binqing.utilproject.adapter.SearchHistoryListAdapter;
import com.binqing.utilproject.adapter.base.BaseViewHolder;
import com.binqing.utilproject.biz.contract.SearchContract;
import com.binqing.utilproject.biz.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements SearchContract.View {

    private ImageView mIvBack;
    private TextView mTvSearch;
    private EditText mEtSearch;
    private RecyclerView mRecyclerView;
    private SearchHistoryListAdapter mAdapter;

    private SearchPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initPresenter();
        initListener();
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshRecyclerView();
    }

    private void initPresenter() {
        mPresenter = new SearchPresenter(this);
    }

    private void initView() {
        mTvSearch = findViewById(R.id.tv_search);
        mEtSearch = findViewById(R.id.et_search);
        mRecyclerView = findViewById(R.id.rv_search_history);
        mIvBack = findViewById(R.id.iv_back);
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

    private void initRecyclerView() {
        final List<String> dataList =new ArrayList<>();
        List<String> list = mPresenter.getHistory();
        if (list != null && !list.isEmpty()) {
            dataList.addAll(list);
        }
        if (!dataList.isEmpty()) {
            dataList.add("");
        }
        final int size = dataList.size();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new SearchHistoryListAdapter(this, dataList, new BaseViewHolder.onItemBaseClickListener() {
            @Override
            public void onItemClickListener(int position) {
                if (position == size-1) {
                    mPresenter.clearHistory();
                } else {
                    mPresenter.search(mAdapter.getItemData(position));
                }
            }

            @Override
            public void onItemLongClickListener(int position) {

            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void refreshRecyclerView() {
        List<String> dataList =new ArrayList<>();
        List<String> list = mPresenter.getHistory();
        if (list != null && !list.isEmpty()) {
            dataList.addAll(list);
        }
        if (!dataList.isEmpty()) {
            dataList.add("");
        }
        mAdapter.setDataList(dataList);
    }

    @Override
    public void updateSearchEdit(String hint) {
        mEtSearch.setHint(hint);
    }
}
