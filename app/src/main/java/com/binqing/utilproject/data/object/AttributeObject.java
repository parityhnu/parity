package com.binqing.utilproject.data.object;

import com.binqing.utilproject.data.model.AttributeModel;

import java.util.ArrayList;
import java.util.List;

public class AttributeObject {
    private String mGid;

    private String mAttribute;

    //与服务器字段不同之处，设定一下只有当需要加载图片时才使用
    private String mImgUrl;

    //跳转
    private String mHref;

    public String getGid() {
        return mGid;
    }

    public void setGid(String gid) {
        mGid = gid;
    }

    public String getAttribute() {
        return mAttribute;
    }

    public void setAttribute(String attribute) {
        mAttribute = attribute;
    }

    public static List<AttributeObject> fromModels(List<AttributeModel> models) {
        if (models == null || models.isEmpty()) {
            return new ArrayList<>();
        }
        List<AttributeObject> result = new ArrayList<>();
        for (AttributeModel model : models) {
            if (model == null) {
                continue;
            }
            result.add(model.toObject());
        }
        return result;
    }

    public String getImgUrl() {
        return mImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        mImgUrl = imgUrl;
    }

    public String getHref() {
        return mHref;
    }

    public void setHref(String href) {
        mHref = href;
    }
}
