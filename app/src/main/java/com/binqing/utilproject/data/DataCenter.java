package com.binqing.utilproject.data;

import android.text.TextUtils;
import android.util.Log;

import com.binqing.utilproject.Callback;
import com.binqing.utilproject.data.model.GoodModel;
import com.binqing.utilproject.data.model.UserModel;
import com.binqing.utilproject.data.object.GoodObject;
import com.binqing.utilproject.data.object.UserObject;

public class DataCenter {

    private static volatile DataCenter mInstance;

    public static DataCenter getInstance() {
        if (mInstance == null) {
            synchronized (DataCenter.class) {
                if (mInstance == null) {
                    mInstance = new DataCenter();
                }
            }
        }
        return mInstance;
    }

    private DataCenter() {
    }

    public void searchGood(GoodObject goodObject, final Callback<GoodObject> callback) {
        if (goodObject == null) {
            return;
        }

        Callback<GoodModel> modelCallback = new Callback<GoodModel>() {
            @Override
            public void onResult(GoodModel object) {
                if (object != null && callback != null) {
                    callback.onResult(object.fromModel());
                } else {
                    Log.e("[DataCenter] searchGood", " GoodModel is null");
                }
            }

            @Override
            public void onException(String code, String reason) {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        };

        DataSourceRemote.getInstance().searchGood(goodObject, modelCallback);
    }

    public void register(String account, String password, final Callback<UserObject> callback) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            return;
        }

        Callback<UserModel> modelCallback = new Callback<UserModel>() {
            @Override
            public void onResult(UserModel object) {
                if (object != null && callback != null) {
                    callback.onResult(object.fromModel());
                } else {
                    callback.onResult(null);
                    Log.e("[DataCenter] register", " UserModel is null");
                }
            }

            @Override
            public void onException(String code, String reason) {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        };

        DataSourceRemote.getInstance().register(account, password, modelCallback);
    }
}
