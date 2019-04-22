package com.binqing.utilproject.data.model;

import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.object.AttributeObject;

public class AttributeModel {

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
