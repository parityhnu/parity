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
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //当前RecyclerView显示出来的最后一个的item的position
                int lastPosition = -1;

                //当前状态为停止滑动状态SCROLL_STATE_IDLE时
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

                    //时判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
                    //如果相等则说明已经滑动到最后了
                    if(lastPosition == recyclerView.getLayoutManager().getItemCount()-1){
                        mPresenter.requestComment();
                    }

                }
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
        int size = attOrCommentOrParityObjects.size();
        for (int i = 0; i < size; i ++) {
            if (attOrCommentOrParityObjects.get(i) == null) {
                continue;
            }
            attOrCommentOrParityObjects.get(i).setIndex(i);
        }
        mParityDetailAdapter.setDataList(attOrCommentOrParityObjects);
    }

    @Override
    public List<AttOrCommentOrParityObject> getDataList() {
        if (mParityDetailAdapter != null) {
            return mParityDetailAdapter.getDataList();
        }
        return null;
    }

}
