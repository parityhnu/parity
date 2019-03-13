package com.binqing.utilproject.data.object;

import java.util.List;

public class GoodsListObject {

    private JDObject mParityJdObjct;

    private TBObject mParityTbObjct;

    private List<JDObject> mJDObjectList;

    private List<TBObject> mTBObjectList;

    public JDObject getParityJdObjct() {
        return mParityJdObjct;
    }

    public void setParityJdObjct(JDObject parityJdObjct) {
        mParityJdObjct = parityJdObjct;
    }

    public TBObject getParityTbObjct() {
        return mParityTbObjct;
    }

    public void setParityTbObjct(TBObject parityTbObjct) {
        mParityTbObjct = parityTbObjct;
    }

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
