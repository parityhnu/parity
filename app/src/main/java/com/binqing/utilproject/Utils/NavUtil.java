package com.binqing.utilproject.Utils;

import android.content.Context;
import android.content.Intent;

import com.binqing.utilproject.Activity.SearchActivity;
import com.binqing.utilproject.Consts.Consts;

public class NavUtil {

    public static void Nav2SearchActivity(Context context, String hint) {
        Intent intent = new Intent();
        intent.putExtra(Consts.INTENT_KEY_SEARCH_GOOD_HINT_NAME, hint);
        intent.setClass(context, SearchActivity.class);
        context.startActivity(intent);
    }


}
