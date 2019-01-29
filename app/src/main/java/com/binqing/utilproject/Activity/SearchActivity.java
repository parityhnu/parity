package com.binqing.utilproject.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.R;
import com.binqing.utilproject.Utils.PreferenceUtil;
import com.binqing.utilproject.adapter.SearchHistoryListAdapter;
import com.binqing.utilproject.adapter.base.BaseViewHolder;
import com.binqing.utilproject.biz.contract.SearchContract;
import com.binqing.utilproject.biz.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements SearchContract.View {

    private TextView mTvSearch;
    private EditText mEtSearch;
    private RecyclerView mRecyclerView;

    private SearchPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initPresenter();
        initListener();
        refreshRecyclerView();
    }

    private void initPresenter() {
        mPresenter = new SearchPresenter(this);
    }

    private void initView() {
        mTvSearch = findViewById(R.id.tv_search);
        mEtSearch = findViewById(R.id.et_search);
        mRecyclerView = findViewById(R.id.rv_search_history);
    }

    private void initListener() {
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goodName = String.valueOf(mEtSearch.getText());
                if ("".equals(goodName)) {
                    goodName = String.valueOf(mEtSearch.getHint());
                }
                mPresenter.search(goodName);
            }
        });
    }

    @Override
    public void refreshRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        final List<String> dataList =new ArrayList<>();
        List<String> list = mPresenter.getHistory();
        if (list != null && !list.isEmpty()) {
            dataList.addAll(list);
        }
        if (!dataList.isEmpty()) {
            dataList.add("");
        }
        mRecyclerView.setAdapter(new SearchHistoryListAdapter(this, dataList, new BaseViewHolder.onItemBaseClickListener() {
            @Override
            public void onItemClickListener(int position) {
                if (position == dataList.size()-1) {
                    mPresenter.clearHistory();
                } else {
                    mPresenter.search(dataList.get(position));
                }
            }

            @Override
            public void onItemLongClickListener(int position) {

            }
        }));
    }

    @Override
    public void updateSearchEdit(String hint) {
        mEtSearch.setHint(hint);
    }
}
