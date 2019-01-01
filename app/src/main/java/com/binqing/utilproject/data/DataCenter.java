package com.binqing.utilproject.data;

import com.binqing.utilproject.Callback;
import com.binqing.utilproject.data.object.GoodObject;

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

        DataSourceRemote.getInstance().searchGood(goodObject, callback);
    }

}
