package com.binqing.utilproject.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.binqing.utilproject.Activity.GoodsListActivity;
import com.binqing.utilproject.Activity.GoodsParityDetailActivity;
import com.binqing.utilproject.Activity.SearchActivity;
import com.binqing.utilproject.Consts.Consts;
import com.binqing.utilproject.data.object.GoodsObject;

import java.io.Serializable;
import java.util.List;

public class NavUtil {

    public static void Nav2SearchActivity(Context context, String hint) {
        Intent intent = new Intent();
        intent.putExtra(Consts.INTENT_KEY_SEARCH_GOODS_HINT_NAME, hint);
        intent.setClass(context, SearchActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2GoodsListActivity(Context context, String goodsName) {
        Intent intent = new Intent();
        intent.putExtra(Consts.INTENT_KEY_SEARCH_GOODS_NAME, goodsName);
        intent.setClass(context, GoodsListActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2GoodsParityDetailActivity(Context context, List<GoodsObject> goodsObjectList) {
        Intent intent = new Intent();
        intent.putExtra(Consts.INTENT_KEY_PARITY_GOODS_DETAIL, (Serializable) goodsObjectList);
        intent.setClass(context, GoodsParityDetailActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2Web(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

}
