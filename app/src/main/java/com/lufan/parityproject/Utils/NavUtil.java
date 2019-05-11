package com.lufan.parityproject.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.lufan.parityproject.Activity.FavoriteActivity;
import com.lufan.parityproject.Activity.ForgetPasswordActivity;
import com.lufan.parityproject.Activity.GoodsListActivity;
import com.lufan.parityproject.Activity.GoodsParityDetailActivity;
import com.lufan.parityproject.Activity.LoginActivity;
import com.lufan.parityproject.Activity.ModifyActivity;
import com.lufan.parityproject.Activity.ModifyDetailActivity;
import com.lufan.parityproject.Activity.SearchActivity;
import com.lufan.parityproject.Activity.SignUpActivity;
import com.lufan.parityproject.Consts.Consts;
import com.lufan.parityproject.Enum.ModifyType;
import com.lufan.parityproject.data.object.ParityObject;

import java.io.Serializable;
import java.util.List;

/**
 * 跳转的工具
 * 需要加数据时用putExtra(key,value)
 * 取数据时intent.getSerializableExtra()
 */
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

    public static void Nav2GoodsParityDetailActivity(Context context, List<ParityObject> parityObjects) {
        Intent intent = new Intent();
        intent.putExtra(Consts.INTENT_KEY_PARITY_GOODS_DETAIL, (Serializable) parityObjects);
        intent.setClass(context, GoodsParityDetailActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2Web(Context context, String url) {
//        Nav2WebViewActivity(context, url);
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
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
        intent.setClass(context, FavoriteActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2ForgetPasswordActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ForgetPasswordActivity.class);
        context.startActivity(intent);
    }

    public static void Nav2WebViewActivity(Context context, String href) {
        Nav2Web(context, href);
//        Intent intent = new Intent();
//        intent.putExtra(Consts.INTENT_KEY_WEBVIEW_HREF, href);
//        intent.setClass(context, WebViewActivity.class);
//        context.startActivity(intent);
    }

}
