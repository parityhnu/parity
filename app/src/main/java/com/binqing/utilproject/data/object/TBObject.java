package com.binqing.utilproject.data.object;

import com.binqing.utilproject.data.model.TBModel;

import java.util.ArrayList;
import java.util.List;

public class TBObject extends GoodsObject{
    private String mSale;

    public String getSale() {
        return mSale;
    }

    public void setSale(String sale) {
        mSale = sale;
    }

    public static TBObject fromModel(TBModel model) {
        if (model != null) {
            TBObject object = new TBObject();
            object.setSale(model.sale);
            object.setName(model.name);
            object.setPrice(model.price);
            object.setHref(model.href);
            object.setImage(model.image);
            object.setKeyword(model.keyword);
            object.setPage(model.page);
            object.setShop(model.shop);
            object.setScore(model.score);
            return object;
        }
        return null;
    }

    public static List<TBObject> fromModels(List<TBModel> modelList) {
        List<TBObject> result = new ArrayList<>();
        for (TBModel model : modelList) {
            if (model != null) {
                TBObject object = new TBObject();
                object.setSale(model.sale);
                object.setName(model.name);
                object.setPrice(model.price);
                object.setHref(model.href);
                object.setImage(model.image);
                object.setKeyword(model.keyword);
                object.setPage(model.page);
                object.setShop(model.shop);
                object.setScore(model.score);
                result.add(object);
            }
        }
        return result;
    }
}
