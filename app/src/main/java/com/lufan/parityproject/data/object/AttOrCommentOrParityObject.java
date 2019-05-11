package com.lufan.parityproject.data.object;

import java.util.List;

/**
 * 构建object之前，需要将相同属性进行一一匹配，且同时只能有几个私有变量当中的一个
 * ParityObject中的属性，也将其解析为XX:XXX格式
 */
public class AttOrCommentOrParityObject {
    private List<AttributeObject> mAttributeObjectList;

    private BaseCommentObject mBaseCommentObject;

    //背景色
    private int index;

    public List<AttributeObject> getAttributeObjectList() {
        return mAttributeObjectList;
    }

    public void setAttributeObjectList(List<AttributeObject> attributeObjectList) {
        mAttributeObjectList = attributeObjectList;
    }

    public BaseCommentObject getBaseCommentObject() {
        return mBaseCommentObject;
    }

    public void setBaseCommentObject(BaseCommentObject baseCommentObject) {
        mBaseCommentObject = baseCommentObject;
    }

    public AttOrCommentOrParityObject(List<AttributeObject> attributeObjectList) {
        mAttributeObjectList = attributeObjectList;
    }

    public AttOrCommentOrParityObject(BaseCommentObject baseCommentObject) {
        mBaseCommentObject = baseCommentObject;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
