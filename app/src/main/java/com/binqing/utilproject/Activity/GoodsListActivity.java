package com.binqing.utilproject.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.binqing.utilproject.Activity.base.BaseActivity;
import com.binqing.utilproject.Enum.SortType;
import com.binqing.utilproject.R;
import com.binqing.utilproject.Utils.NavUtil;
import com.binqing.utilproject.adapter.GoodsListAdapter;
import com.binqing.utilproject.adapter.ParityListAdapter;
import com.binqing.utilproject.adapter.base.BaseViewHolder;
import com.binqing.utilproject.biz.contract.GoodsListContract;
import com.binqing.utilproject.biz.presenter.GoodsListPresenter;
import com.binqing.utilproject.data.object.GoodsObject;
import com.binqing.utilproject.data.object.ParityObject;

import java.util.ArrayList;
import java.util.List;

public class GoodsListActivity extends BaseActivity implements GoodsListContract.View {
    
    private GoodsListPresenter mPresenter;
    
    private ImageView mIvBack;
    private TextView mTvSearch;
    private EditText mEtSearch;
    private ProgressBar mPbLoad;
    private RecyclerView mRvGoodsList;
    private TextView mTvSortPrice;
    private TextView mTvSortSaleComment;
    private TextView mTvSortComprehensive;

    private GoodsListAdapter mGoodsListAdapter;

    private SortType mSortStatus = SortType.INIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        initView();
        initPresenter();
        initRecyclerView();
        initListener();
        mPresenter.requsetGoods("0", SortType.INIT);
    }

    private void initPresenter() {
        mPresenter = new GoodsListPresenter(this);
    }

    private void initView() {
        mTvSearch = findViewById(R.id.tv_search);
        mIvBack = findViewById(R.id.iv_back);
        mEtSearch = findViewById(R.id.et_search);
        mPbLoad = findViewById(R.id.progressBar);
        mRvGoodsList = findViewById(R.id.rv_goods_list);
        mTvSortPrice = findViewById(R.id.tv_price);
        mTvSortComprehensive = findViewById(R.id.tv_comprehensive);
        mTvSortSaleComment = findViewById(R.id.tv_sales_volume_or_comment);
    }

    private void initListener() {
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goodsName = String.valueOf(mEtSearch.getText());
                if ("".equals(goodsName)) {
                    goodsName = String.valueOf(mEtSearch.getHint());
                }
                mPresenter.search(goodsName, SortType.INIT);
            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTvSortComprehensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSortStatus = SortType.INIT;
                mPresenter.search(mPresenter.getGoodName(), SortType.INIT);
            }
        });

        mTvSortPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSortStatus != SortType.PRICE_ASC) {
                    mSortStatus = SortType.PRICE_ASC;
                    mPresenter.search(mPresenter.getGoodName(), SortType.PRICE_ASC);
                } else {
                    mSortStatus = SortType.PRICE_DESC;
                    mPresenter.search(mPresenter.getGoodName(), SortType.PRICE_DESC);
                }
            }
        });

        mTvSortSaleComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSortStatus = SortType.SALE_COMMENT_DESC;
                mPresenter.search(mPresenter.getGoodName(), SortType.SALE_COMMENT_DESC);
            }
        });

        mRvGoodsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        mPresenter.requsetGoods(String.valueOf(Integer.parseInt(mPresenter.getPage()) + 1), mSortStatus);
                    }

                }
            }
        });
    }

    private void initRecyclerView() {
        List<GoodsObject> dataList = new ArrayList<>();
        mRvGoodsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mGoodsListAdapter = new GoodsListAdapter(this, dataList, new BaseViewHolder.onItemBaseClickListener() {
            @Override
            public void onItemClickListener(int position) {
                GoodsObject goodsObject = mGoodsListAdapter.getItemData(position);
                String url = goodsObject.getHref();
                NavUtil.Nav2Web(getApplication(), "https:" + url);
            }

            @Override
            public void onItemLongClickListener(int position) {

            }
        });
        mRvGoodsList.setAdapter(mGoodsListAdapter);




    }

    @Override
    public void refreshList(List<GoodsObject> dataList) {
        mGoodsListAdapter.setDataList(dataList);
    }

    @Override
    public void updateSearchEdit(String hint) {
        mEtSearch.setHint(hint);
    }

    @Override
    public void onLoad() {
        mPbLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadFinish() {
        mPbLoad.setVisibility(View.GONE);
    }

    @Override
    public List<GoodsObject> getDataList() {
        if(mGoodsListAdapter == null) {
            return null;
        }
        return mGoodsListAdapter.getDataList();
    }

}
