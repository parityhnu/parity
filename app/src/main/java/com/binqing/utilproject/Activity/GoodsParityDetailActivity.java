package com.binqing.utilproject.Activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

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

    private TextView mTvFavorite;
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
        mTvFavorite = findViewById(R.id.tv_favorite);
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
        mTvFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopMenu(mTvFavorite, mPresenter.getParity());
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

    @Override
    public void initFavoriteState() {
        List<ParityObject> parityObjectList = mPresenter.getParity();
        if (parityObjectList == null) {
            return;
        }
        boolean hasFavorited = false;
        for (ParityObject parityObject : parityObjectList) {
            if (parityObject == null) {
                continue;
            }
            if (mPresenter.getFavoriteState(parityObject.getGid())) {
                hasFavorited = true;
                break;
            }
        }
        updateFavoriteState(true);
    }

    @Override
    public void updateFavoriteState(boolean hasFavorited) {
        if (hasFavorited) {
            mTvFavorite.setText("已收藏");
        } else {
            mTvFavorite.setText("收藏");
        }
    }

    @Override
    public void alert(boolean hasFavorited) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(hasFavorited?"收藏成功":"取消收藏");
        builder.show();
    }

    private void showPopMenu(View view, final List<ParityObject> menuList) {
        if (menuList == null || menuList.isEmpty()) {
            return;
        }
        PopupMenu popupMenu = new PopupMenu(this, view);
        android.view.Menu menu_more = popupMenu.getMenu();
        int size = menuList.size();
        for (int i = 0; i < size; i++) {
            if (mPresenter.getFavoriteState(menuList.get(i).getGid())) {
                menu_more.add(android.view.Menu.NONE, android.view.Menu.FIRST + i, i, "(已)" + menuList.get(i).getName());
            } else {
                menu_more.add(android.view.Menu.NONE, android.view.Menu.FIRST + i, i, menuList.get(i).getName());
            }
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int i = item.getItemId();
                mPresenter.favorite(menuList.get(i-1));
                return true;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                backgroundAlpha(1);
            }
        });
        popupMenu.show();
        backgroundAlpha((float) 0.8);
    }

    private void backgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha; //0.0-1.0
        getWindow().setAttributes(lp);  getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

}
