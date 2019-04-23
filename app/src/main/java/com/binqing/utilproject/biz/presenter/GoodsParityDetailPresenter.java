package com.binqing.utilproject.biz.presenter;

import android.content.Intent;
import android.widget.Toast;

import com.binqing.utilproject.Activity.GoodsParityDetailActivity;
import com.binqing.utilproject.Callback;
import com.binqing.utilproject.Consts.Consts;
import com.binqing.utilproject.Utils.LogUtils;
import com.binqing.utilproject.biz.contract.GoodsParityDetailContract;
import com.binqing.utilproject.data.DataProvider;
import com.binqing.utilproject.data.object.AttOrCommentOrParityObject;
import com.binqing.utilproject.data.object.AttributeObject;
import com.binqing.utilproject.data.object.BaseCommentObject;
import com.binqing.utilproject.data.object.CommentReturnObject;
import com.binqing.utilproject.data.object.JDCommentObject;
import com.binqing.utilproject.data.object.ParityObject;
import com.binqing.utilproject.data.object.TBCommentObject;
import com.binqing.utilproject.data.object.TMCommentObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GoodsParityDetailPresenter implements GoodsParityDetailContract.Presenter {

    private GoodsParityDetailActivity mView;
    private List<ParityObject> mData;
    private int mCommentIndex = 1;

    public GoodsParityDetailPresenter(GoodsParityDetailActivity activity) {
        bindView(activity);
        initData();
    }

    private void bindView(GoodsParityDetailActivity activity) {
        mView = activity;
    }


    private void initData() {
        Intent intent = mView.getIntent();
        mData = (List<ParityObject>) intent.getSerializableExtra(Consts.INTENT_KEY_PARITY_GOODS_DETAIL);
        List<AttOrCommentOrParityObject> dataList = mView.getDataList();
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        List<AttOrCommentOrParityObject> attOrCommentOrParityObject = dataToData();
        dataList.addAll(attOrCommentOrParityObject);
        mView.refreshView(dataList);
        requestAttributeAndComment();
    }

    private void requestAttributeAndComment() {
        if (mData == null || mData.isEmpty()) {
            return;
        }
        List<String> ids = new ArrayList<>();
        for (ParityObject parityObject : mData) {
            ids.add(parityObject.getTypeGid());
        }
        requestComment(ids);
        requestAttribute(ids);
    }

    private void requestComment(List<String> ids) {
        DataProvider.getInstance().getComments(ids, String.valueOf(mCommentIndex), new Callback<CommentReturnObject>() {
            @Override
            public void onResult(CommentReturnObject result) {
                if (result == null) {
                    return;
                }
                List<JDCommentObject> jdCommentObjects = result.getJDCommentObjects();
                List<TMCommentObject> tmCommentObjects = result.getTMCommentObjects();
                List<TBCommentObject> tbCommentObjects = result.getTBCommentObjects();
                List<BaseCommentObject> baseCommentObjects = new ArrayList<>();
                if (jdCommentObjects != null) {
                    baseCommentObjects.addAll(jdCommentObjects);
                }
                if (tbCommentObjects != null) {
                    baseCommentObjects.addAll(tbCommentObjects);
                }
                if (tmCommentObjects != null) {
                    baseCommentObjects.addAll(tmCommentObjects);
                }
                Collections.sort(baseCommentObjects);
                List<AttOrCommentOrParityObject> dataList = mView.getDataList();
                if (dataList == null) {
                    dataList = new ArrayList<>();
                }
                for (BaseCommentObject baseCommentObject : baseCommentObjects) {
                    if (baseCommentObject == null) {
                        continue;
                    }
                    dataList.add(new AttOrCommentOrParityObject(baseCommentObject));
                }
                mCommentIndex = mCommentIndex + 1;
                mView.refreshView(dataList);
            }

            @Override
            public void onException(String code, String reason) {
                Toast.makeText(mView, reason, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestAttribute(List<String> ids) {
        DataProvider.getInstance().getAttributes(ids, new Callback<List<AttributeObject>>() {
            @Override
            public void onResult(List<AttributeObject> result) {
                if (result == null) {
                    return;
                }
                Map<String, List<AttributeObject>> attributeMap = new HashMap<>();
                for (AttributeObject attributeObject : result) {
                    if (attributeMap.get(attributeObject.getGid()) == null) {
                        attributeMap.put(attributeObject.getGid(), new ArrayList<AttributeObject>());
                    }
                    attributeMap.get(attributeObject.getGid()).add(attributeObject);
                }
                Map<String, List<String>> stringListMap = dealWithAtrributeMap(attributeMap);

                List<AttOrCommentOrParityObject> dataList = mView.getDataList();
                if (dataList == null) {
                    dataList = new ArrayList<>();
                }
                dataList.addAll(dealWithStringListMap(stringListMap));
                mView.refreshView(dataList);
            }

            @Override
            public void onException(String code, String reason) {
                Toast.makeText(mView, reason, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Map<String, List<String>> dealWithAtrributeMap(Map<String, List<AttributeObject>> attributeMap) {
        if (attributeMap == null || attributeMap.isEmpty()) {
            return null;
        }
        if (mData == null || mData.isEmpty()) {
            return null;
        }
        Map<String, List<String>> stringListMap = new LinkedHashMap<>();
        int index = 0;
        for (ParityObject parityObject : mData) {
            List<AttributeObject> attributeObjectList = attributeMap.get(parityObject.getGid());
            if (attributeObjectList == null) {
                index ++;
                continue;
            }
            for (AttributeObject attributeObject : attributeObjectList) {
                String attribute = attributeObject.getAttribute();
                String[] strings = attribute.split(":",2);
                if (strings.length < 2) {
                    strings = attribute.split("：", 2);
                }
                if (strings.length == 2) {
                    strings[0] = strings[0].replaceAll("\n", "");
                    strings[1] = strings[1].replaceAll("\n", "");
                    if (stringListMap.get(strings[0]) == null) {
                        stringListMap.put(strings[0], new ArrayList<String>());
                    }
                    if (index == 1) {
                        stringListMap.get(strings[0]).add(" ");
                    }
                    stringListMap.get(strings[0]).add(strings[1]);
                }
            }
            index ++;
        }
        return stringListMap;
    }

    private List<AttOrCommentOrParityObject> dataToData() {
        List<AttOrCommentOrParityObject> result = new ArrayList<>();
        if (mData != null && !mData.isEmpty()) {
            Map<String, List<String>> stringListMap = new LinkedHashMap<>();
            stringListMap.put("商品", new ArrayList<String>());
            stringListMap.put("价格", new ArrayList<String>());
            stringListMap.put("销量", new ArrayList<String>());
            stringListMap.put("店铺", new ArrayList<String>());

            ParityObject parityObject = mData.get(0);
            String name = parityObject.getName();
            String price = parityObject.getPrice();
            int saleComment = parityObject.getSalecomment();
            String shop = parityObject.getShop();
            stringListMap.get("商品").add(name);
            stringListMap.get("价格").add(price);
            stringListMap.get("销量").add(String.valueOf(saleComment));
            stringListMap.get("店铺").add(shop);

            if (mData.size() > 1) {
                parityObject = mData.get(1);
                name = parityObject.getName();
                price = parityObject.getPrice();
                saleComment = parityObject.getSalecomment();
                shop = parityObject.getShop();
                stringListMap.get("商品").add(name);
                stringListMap.get("价格").add(price);
                stringListMap.get("销量").add(String.valueOf(saleComment));
                stringListMap.get("店铺").add(shop);
            }

            result.addAll(dealWithStringListMap(stringListMap));

        }
        return result;
    }

    private List<AttOrCommentOrParityObject> dealWithStringListMap(Map<String, List<String>> stringListMap) {
        if (stringListMap == null) {
            return new ArrayList<>();
        }
        List<AttOrCommentOrParityObject> result = new ArrayList<>();
        Set<Map.Entry<String, List<String>>> set = stringListMap.entrySet();
        for (Map.Entry<String, List<String>> entry : set) {
            List<AttributeObject> attributeObjectList = new ArrayList<>();
            String key = entry.getKey();
            List<String> value = entry.getValue();
            if (value == null || value.isEmpty()) {
                continue;
            }
            int size = value.size();
            int ld;
            if (mData != null) {
                ld = mData.size();
            } else {
                ld = 0;
            }
            for (int i = 0; i < size; i ++) {
                AttributeObject attributeObject = new AttributeObject();
                attributeObject.setAttribute(key + ":" + value.get(i));
                if ("商品".equals(key)) {
                    if (i < ld) {
                        attributeObject.setImgUrl(mData.get(i).getImage());
                    }
                }
                attributeObjectList.add(attributeObject);
            }
            result.add(new AttOrCommentOrParityObject(attributeObjectList));
        }
        return result;
    }

    @Override
    public void requestComment() {
        if (mData == null || mData.isEmpty()) {
            return;
        }
        List<String> ids = new ArrayList<>();
        for (ParityObject parityObject : mData) {
            ids.add(parityObject.getTypeGid());
        }
        requestComment(ids);
    }
}
