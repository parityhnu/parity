package com.lufan.parityproject.data.model;

import com.lufan.parityproject.data.annotation.Member;
import com.lufan.parityproject.data.object.AttributeObject;

public class AttributeModel {
   // [{"gid":2222222, "attribute":"213123123"},{"gid":2222222, "attribute":"213123123"}]
    @Member(order = 0)
    public String gid;

    @Member(order = 1)
    public String attribute;

    public AttributeObject toObject() {
        AttributeObject attributeObject = new AttributeObject();
        attributeObject.setAttribute(attribute);
        attributeObject.setGid(gid);
        return attributeObject;
    }
}
