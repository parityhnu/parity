package com.binqing.utilproject.data.object;

import java.util.List;

public class GoodsListObject {
    private List<JDObject> mJDObjectList;

    private List<TBObject> mTBObjectList;

    public List<JDObject> getJDObjectList() {
        return mJDObjectList;
    }

    public void setJDObjectList(List<JDObject> JDObjectList) {
        mJDObjectList = JDObjectList;
    }

    public List<TBObject> getTBObjectList() {
        return mTBObjectList;
    }

    public void setTBObjectList(List<TBObject> TBObjectList) {
        mTBObjectList = TBObjectList;
    }
}
