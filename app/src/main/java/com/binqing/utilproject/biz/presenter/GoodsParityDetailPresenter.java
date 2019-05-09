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
    private Map<String, Boolean> mFavoriteState;
    private int mCommentIndex = 1;

    public GoodsParityDetailPresenter(GoodsParityDetailActivity activity) {
        bindView(activity);
        mFavoriteState = new HashMap<>();
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
        checkFavoriteState();
        requestAttributeAndComment();
    }

    private void checkFavoriteState() {
        if (mData == null) {
            return;
        }
        for (final ParityObject parityObject : mData) {
            if (parityObject == null) {
                continue;
            }
            DataProvider.getInstance().checkFavorite(parityObject.getTypeGid(),
                    parityObject.getKeyword(),
                    String.valueOf(parityObject.getSort()),
                    new Callback<String>() {
                @Override
                public void onResult(String result) {
                    if (parityObject.getGid().equals(result)) {
                        mFavoriteState.put(parityObject.getGid(), true);
                    } else {
                        mFavoriteState.put(parityObject.getGid(), false);
                    }
                    mView.initFavoriteState();
                }

                @Override
                public void onException(String code, String reason) {

                }
            });
        }
    }

    private void requestAttributeAndComment() {
        if (mData == null || mData.isEmpty()) {
            return;
        }
        List<String> ids = new ArrayList<>();
        for (ParityObject parityObject : mData) {
            ids.add(parityObject.getTypeGid());
        }

        requestAttribute(ids);
    }

    private void requestComment(List<String> ids) {
        DataProvider.getInstance().getComments(ids, String.valueOf(mCommentIndex), new Callback<CommentReturnObject>() {
            @Override
            public void onResult(CommentReturnObject result) {
                if (result == null) {
                    return;
                }
                //以下几句是将得到的各种评论的对象，加到一个列表里排序
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
                Collections.sort(baseCommentObjects);//排序
                List<AttOrCommentOrParityObject> dataList = mView.getDataList();
                if (dataList == null) {
                    dataList = new ArrayList<>();
                }
                for (BaseCommentObject baseCommentObject : baseCommentObjects) {
                    if (baseCommentObject == null) {
                        continue;
                    }
                    //这个AttOrCommentOrParityObject的属性，参数或者评论只能取其一
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

    private void requestAttribute(final List<String> ids) {
        DataProvider.getInstance().getAttributes(ids, new Callback<List<AttributeObject>>() {
            @Override
            public void onResult(List<AttributeObject> result) {
                requestComment(ids);
                if (result == null) {
                    return;
                }
                Map<String, List<AttributeObject>> attributeMap = new HashMap<>();
                //下面这个循环，是根据id来散列，将列表根据id的不同分开
                for (AttributeObject attributeObject : result) {
                    if (attributeMap.get(attributeObject.getGid()) == null) {
                        attributeMap.put(attributeObject.getGid(), new ArrayList<AttributeObject>());
                    }
                    attributeMap.get(attributeObject.getGid()).add(attributeObject);
                }
                //以下这一句是将上述的结果，根据参数名进行散列
                Map<String, List<String>> stringListMap = dealWithAtrributeMap(attributeMap);

                //以下几句的意思是，得到现有在列表里的数据，然后将新的数据加进去，然后刷新列表
                List<AttOrCommentOrParityObject> dataList = mView.getDataList();
                if (dataList == null) {
                    dataList = new ArrayList<>();
                }
                dataList.addAll(dealWithStringListMap(stringListMap));
                mView.refreshView(dataList);
            }

            @Override
            public void onException(String code, String reason) {
                requestComment(ids);
                Toast.makeText(mView, reason, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Map<String, List<String>> dealWithAtrributeMap(Map<String, List<AttributeObject>> attributeMap) {
        //传进来的变量是根据id散列好的
        if (attributeMap == null || attributeMap.isEmpty()) {
            return null;
        }
        if (mData == null || mData.isEmpty()) {
            return null;
        }
        Map<String, List<String>> stringListMap = new LinkedHashMap<>();
        int index = 0;
        //此处的id一定是和ParityObjects中的一一对应的，不然就是错的
        for (ParityObject parityObject : mData) {
            List<AttributeObject> attributeObjectList = attributeMap.get(parityObject.getGid());
            if (attributeObjectList == null) {
                index ++;
                continue;
            }
            for (AttributeObject attributeObject : attributeObjectList) {
                String attribute = attributeObject.getAttribute();
                //参数都是形如 "前置摄像头：1200万像素" ，因此根据冒号分割
                String[] strings = attribute.split(":",2);
                if (strings.length < 2) {
                    strings = attribute.split("：", 2);
                }
                if (strings.length == 2) {
                    strings[0] = strings[0].replaceAll("\n", "");
                    strings[1] = strings[1].replaceAll("\n", "");
                    //如果有这个key了，那么会将value插到这个key下面， 如果没有的话就新建一个key-value
                    if (stringListMap.get(strings[0]) == null) {
                        stringListMap.put(strings[0], new ArrayList<String>());
                        //前面一个商品没有该参数，占位用
                        if (index == 1) {
                            stringListMap.get(strings[0]).add(" ");
                        }
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
            switch (parityObject.getType()) {
                case 0:
                    shop += "(京东)";
                    break;
                case 1:
                    shop += "(淘宝)";
                    break;
                case 2:
                    shop += "(天猫)";
                    break;
                default:
                    break;
            }
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
                switch (parityObject.getType()) {
                    case 0:
                        shop += "(京东)";
                        break;
                    case 1:
                        shop += "(淘宝)";
                        break;
                    case 2:
                        shop += "(天猫)";
                        break;
                    default:
                        break;
                }
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
                        attributeObject.setParityObject(mData.get(i));
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

    @Override
    public List<ParityObject> getParity() {
        return mData;
    }

    @Override
    public void favorite(final ParityObject parityObject) {
        boolean cancel = mFavoriteState.get(parityObject.getGid());
        DataProvider.getInstance().favorite(parityObject.getTypeGid(),
                parityObject.getKeyword(),
                String.valueOf(parityObject.getSort()),
                cancel,
                new Callback<String>() {
            @Override
            public void onResult(String result) {
                if (result != null) {
                    String [] strings = result.split("_");
                    if (strings.length < 2) {
                        return;
                    }
                    String id = strings[0];
                    String canceled = strings[1];
                    if (id.equals(parityObject.getGid())) {

                        if ("true".equals(canceled)) {
                            //已经取消收藏
                            mFavoriteState.put(parityObject.getGid(), false);
                            mView.updateFavoriteState(false);
                            mView.alert(false);
                        } else {
                            mFavoriteState.put(parityObject.getGid(), true);
                            mView.updateFavoriteState(true);
                            mView.alert(true);
                        }
                    }
                }
            }

            @Override
            public void onException(String code, String reason) {

            }
        });
    }

    @Override
    public boolean getFavoriteState(String gid) {
        if (mFavoriteState.get(gid) == null) {
            return false;
        }
        return mFavoriteState.get(gid);
    }
}
