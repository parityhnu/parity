package com.lufan.parityproject.biz.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.lufan.parityproject.Activity.GoodsListActivity;
import com.lufan.parityproject.Callback;
import com.lufan.parityproject.Consts.Consts;
import com.lufan.parityproject.Enum.SortType;
import com.lufan.parityproject.Utils.PreferenceUtil;
import com.lufan.parityproject.biz.contract.GoodsListContract;
import com.lufan.parityproject.data.DataProvider;
import com.lufan.parityproject.data.object.GoodsListObject;
import com.lufan.parityproject.data.object.GoodsObject;
import com.lufan.parityproject.data.object.ParityObject;
import com.lufan.parityproject.data.object.SearchObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GoodsListPresenter implements GoodsListContract.Presenter {

    private GoodsListActivity mView;
    private String mGoodsName;
    private String mPage = "0";

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
                List<ParityObject> parityObjects = goodsListObject.getParityObjectList();
                if (parityObjects != null && !parityObjects.isEmpty()) {
                    GoodsObject goodsObject = new GoodsObject();
                    goodsObject.setKeyword(parityObjects.get(0).getKeyword());
                    List<List<ParityObject>> listList = new ArrayList<>();
                    int num = 0;
                    int index = 0;//用index=0或1来判断比价商品两个一组
                    for (ParityObject parityObject : parityObjects) {
                        if (parityObject == null) {
                            continue;
                        }
                        if (index == 0) {
                            listList.add(new ArrayList<ParityObject>());
                            listList.get(num).add(parityObject);
                            index = 1;
                        } else {
                            if (parityObject.getOrder() == listList.get(num).get(0).getOrder()) {
                                listList.get(num).add(parityObject);
                                num ++;
                                index = 0;
                            } else {
                                num ++;
                                listList.add(new ArrayList<ParityObject>());
                                listList.get(num).add(parityObject);
                                index = 1;
                            }
                        }
                    }
                    goodsObject.setParityObjects(listList);
                    dataList.add(goodsObject);
                }
                dataList.addAll(goodsListObject.getGoodsObjectList());
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


}
