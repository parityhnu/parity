package com.binqing.utilproject.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.binqing.utilproject.Activity.CollectionActivity;
import com.binqing.utilproject.Activity.ForgetPasswordActivity;
import com.binqing.utilproject.Activity.GoodsListActivity;
import com.binqing.utilproject.Activity.GoodsParityDetailActivity;
import com.binqing.utilproject.Activity.LoginActivity;
import com.binqing.utilproject.Activity.ModifyActivity;
import com.binqing.utilproject.Activity.ModifyDetailActivity;
import com.binqing.utilproject.Activity.SearchActivity;
import com.binqing.utilproject.Activity.SignUpActivity;
import com.binqing.utilproject.Activity.WebViewActivity;
import com.binqing.utilproject.Consts.Consts;
import com.binqing.utilproject.Enum.ModifyType;
import com.binqing.utilproject.data.object.GoodsObject;
import com.binqing.utilproject.data.object.ParityObject;

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

    public static void Nav2GoodsParityDetailActivity(Context context, List<ParityObject> goodsObjectList) {
        Intent intent = new Intent();
        intent.putExtra(Consts.INTENT_KEY_PARITY_GOODS_DETAIL, (Serializable) goodsObjectList);
        intent.setClass(context, GoodsParityDetailActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2Web(Context context, String url) {
        Nav2WebViewActivity(context, url);
//        Uri uri = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        context.startActivity(intent);
    }

    public static void Nav2SignUpActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2LoginActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2ModifyActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ModifyActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2ModifyDetailActivity(Context context, ModifyType modifyType) {
        Intent intent = new Intent();
        intent.putExtra(Consts.INTENT_KEY_MODIFY_TYPE, modifyType);
        intent.setClass(context, ModifyDetailActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2CollectionActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CollectionActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2ForgetPasswordActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ForgetPasswordActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2WebViewActivity(Context context, String href) {
        Intent intent = new Intent();
        intent.putExtra(Consts.INTENT_KEY_WEBVIEW_HREF, href);
        intent.setClass(context, WebViewActivity.class);
        context.startActivity(intent);
    }

}
