package com.binqing.utilproject.biz.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.binqing.utilproject.Activity.GoodsListActivity;
import com.binqing.utilproject.Callback;
import com.binqing.utilproject.Consts.Consts;
import com.binqing.utilproject.Enum.SortType;
import com.binqing.utilproject.R;
import com.binqing.utilproject.Utils.PreferenceUtil;
import com.binqing.utilproject.biz.contract.GoodsListContract;
import com.binqing.utilproject.data.DataProvider;
import com.binqing.utilproject.data.object.GoodsListObject;
import com.binqing.utilproject.data.object.GoodsObject;
import com.binqing.utilproject.data.object.JDObject;
import com.binqing.utilproject.data.object.SearchObject;
import com.binqing.utilproject.data.object.TBObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GoodsListPresenter implements GoodsListContract.Presenter {

    private GoodsListActivity mView;
    private String mGoodsName;
    private String mPage = "";

    public GoodsListPresenter(GoodsListActivity activity) {
        bindView(activity);
        dealWithIntent();
    }

    private void dealWithIntent() {
        Intent intent = mView.getIntent();
        Bundle bundle = intent.getExtras();
        String goodsName = bundle.getString(Consts.INTENT_KEY_SEARCH_GOODS_NAME);
        mGoodsName = goodsName;
        mView.updateSearchEdit(mGoodsName);
    }

    private void bindView(GoodsListActivity activity) {
        mView = activity;
    }

    @Override
    public void search(String goodsName, SortType sort) {
        if ("".equals(goodsName)) {
            return;
        }
        if (sort == null) {
            sort = SortType.INIT;
        }
        mView.refreshList(new ArrayList<GoodsObject>());
        String history = PreferenceUtil.getUserString(mView, Consts.PREDERENCE_SEARCH_HISTORY);
        List<String> list = new ArrayList<>();
        Gson gson = new Gson();
        if (!"".equals(history) && history != null) {
            list = gson.fromJson(history, new TypeToken<List<String>>(){}.getType());
        }
        if (list.size() >= 10) {
            list.remove(9);
        }
        list.remove(goodsName);
        list.add(0,goodsName);
        history = gson.toJson(list);
        PreferenceUtil.setUserString(mView, Consts.PREDERENCE_SEARCH_HISTORY, history);
        mGoodsName = goodsName;
        requsetGoods("0",  sort);
    }

    @Override
    public void requsetGoods(final String page, final SortType sort) {
        if ("0".equals(page)) {
            mView.onLoad();
        }
        SearchObject object = new SearchObject(mGoodsName, page, sort.getValue());
        //todo 设置网络错误或超时的toast
        DataProvider.getInstance().searchGood(object, new Callback<GoodsListObject>() {
            @Override
            public void onResult(GoodsListObject goodsListObject) {
                mView.onLoadFinish();
                mPage = page;
                List<GoodsObject> dataList = new ArrayList<>();
                JDObject jdObject = goodsListObject.getParityJdObjct();
                TBObject tbObject = goodsListObject.getParityTbObjct();
                List<GoodsObject> parityObjects = new ArrayList<>();
                if (jdObject != null) {
                    parityObjects.add(jdObject);
                }
                if (tbObject != null) {
                    parityObjects.add(tbObject);
                }
                dataList.addAll(goodsListObject.getJDObjectList());
                dataList.addAll(goodsListObject.getTBObjectList());
                sortGoods(dataList, sort);
                if (!parityObjects.isEmpty()) {
                    GoodsObject goodsObject = parityObjects.get(0);
                    goodsObject.setParityObjects(parityObjects);
                    dataList.add(0,goodsObject);
                }
                if (!dataList.isEmpty()) {
                    dataList.add(new GoodsObject());
                }
                if (!"0".equals(page)) {
                    List<GoodsObject> before = mView.getDataList();
                    if (before != null && !before.isEmpty()) {
                        int size = before.size();
                        before.remove(size-1);
                        dataList.addAll(0,before);
                    }
                }
                mView.refreshList(dataList);
            }

            @Override
            public void onException(String code, String reason) {
                Toast.makeText(mView, "网络错误", Toast.LENGTH_SHORT).show();
                mView.onLoadFinish();
            }
        });
    }

    @Override
    public String getGoodName() {
        return mGoodsName;
    }

    @Override
    public String getPage() {
        return mPage;
    }

    private List<GoodsObject> sortGoods(List<GoodsObject> dataList, SortType sort) {
        switch (sort) {
            case INIT:
                Collections.sort(dataList, new Comparator<GoodsObject>() {
                    @Override
                    public int compare(GoodsObject o1, GoodsObject o2) {
                        return o2.getScore() - o1.getScore();
                    }
                });
                return dataList;
            case PRICE_ASC:
                Collections.sort(dataList, new Comparator<GoodsObject>() {
                    @Override
                    public int compare(GoodsObject o1, GoodsObject o2) {
                        double d = Double.parseDouble(o1.getPrice()) - Double.parseDouble(o2.getPrice());
                        return (int) (d * 100);
                    }
                });
                return dataList;
            case PRICE_DESC:
                Collections.sort(dataList, new Comparator<GoodsObject>() {
                    @Override
                    public int compare(GoodsObject o1, GoodsObject o2) {
                        double d = Double.parseDouble(o2.getPrice()) - Double.parseDouble(o1.getPrice());
                        return (int) (d * 100);
                    }
                });
                return dataList;
            case SALE_COMMENT_DESC:
                Collections.sort(dataList, new Comparator<GoodsObject>() {
                    @Override
                    public int compare(GoodsObject o1, GoodsObject o2) {
                        return o2.getSaleOrComment() - o1.getSaleOrComment();
                    }
                });
                return dataList;
            default:
                return dataList;
        }
    }

}
