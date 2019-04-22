package com.binqing.utilproject.data.object;

import com.binqing.utilproject.data.model.AttributeModel;

import java.util.ArrayList;
import java.util.List;

public class AttributeObject {
    private String mGid;

    private String mAttribute;

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
}
