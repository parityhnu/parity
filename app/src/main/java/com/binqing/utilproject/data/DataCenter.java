package com.binqing.utilproject.data;

import android.text.TextUtils;
import android.util.Log;

import com.binqing.utilproject.Callback;
import com.binqing.utilproject.Enum.ModifyType;
import com.binqing.utilproject.ParityApplication;
import com.binqing.utilproject.data.model.AttributeModel;
import com.binqing.utilproject.data.model.CommentReturnModel;
import com.binqing.utilproject.data.model.GoodsListModel;
import com.binqing.utilproject.data.model.ParityModel;
import com.binqing.utilproject.data.model.StringModel;
import com.binqing.utilproject.data.model.UserModel;
import com.binqing.utilproject.data.object.AttributeObject;
import com.binqing.utilproject.data.object.CommentReturnObject;
import com.binqing.utilproject.data.object.GoodsListObject;
import com.binqing.utilproject.data.object.ParityObject;
import com.binqing.utilproject.data.object.SearchObject;
import com.binqing.utilproject.data.object.UserObject;

import java.util.List;

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

    public void searchGood(SearchObject searchObject, final Callback<GoodsListObject> callback) {
        if (searchObject == null) {
            return;
        }

        Callback<GoodsListModel> modelCallback = new Callback<GoodsListModel>() {
            @Override
            public void onResult(GoodsListModel modelList) {
                if (modelList != null && callback != null) {
                    callback.onResult(modelList.toObjct());
                } else {
                    Log.e("[DataCenter]searchGoods", " modelList is null");
                }
            }

            @Override
            public void onException(String code, String reason) {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        };

        DataSourceRemote.getInstance().searchGoods(searchObject, modelCallback);
    }

    public void register(String account, String password, String phone, final Callback<UserObject> callback) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone)) {
            return;
        }

        Callback<UserModel> modelCallback = new Callback<UserModel>() {
            @Override
            public void onResult(UserModel object) {
                if (object != null && callback != null) {
                    callback.onResult(object.toObject());
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

        DataSourceRemote.getInstance().register(account, password, phone, modelCallback);
    }

    public void login(String account, String password, final Callback<UserObject> callback) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            return;
        }

        Callback<UserModel> modelCallback = new Callback<UserModel>() {
            @Override
            public void onResult(UserModel model) {
                if (model != null && callback != null) {
                    callback.onResult(model.toObject());
                } else {
                    callback.onResult(null);
                    Log.e("[DataCenter] login", " UserModel is null");
                }
            }

            @Override
            public void onException(String code, String reason) {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        };

        DataSourceRemote.getInstance().login(account, password, modelCallback);
    }

    public void modify(String s1, String s2, final ModifyType modifyType, final Callback<String> callback) {
        int user = ParityApplication.getInstance().getUserId();
        if (user == 0) {
            return;
        }
        if (callback == null) {
            return;
        }
        Callback<StringModel> callback1 = new Callback<StringModel>() {
            @Override
            public void onResult(StringModel result) {
                if (callback == null) {
                    return;
                }
                if (result == null) {
                    callback.onException("-1", "stringmodel is null");
                    return;
                }
                switch (modifyType) {
                    case PHONE:
                        ParityApplication.getInstance().setPhone(result.string);
                        break;
                    case NAME:
                        ParityApplication.getInstance().setAccountName(result.string);
                        break;
                    default:
                        break;
                }
                callback.onResult(result.string);
            }

            @Override
            public void onException(String code, String reason) {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        };

        DataSourceRemote.getInstance().modify(user, s1, s2, modifyType.getValue(), callback1);
    }

    public void forgetPassword(String account, String phone, String password, final Callback<String> callback) {
        Callback<StringModel> callback1 = new Callback<StringModel>() {
            @Override
            public void onResult(StringModel result) {
                if (callback == null) {
                    return;
                }
                if (result == null) {
                    callback.onException("-1", "stringmodel is null");
                    return;
                }
                callback.onResult(result.string);
            }

            @Override
            public void onException(String code, String reason) {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        };
        DataSourceRemote.getInstance().forgetPassword(account, phone, password, callback1);
    }

    public void requestPhone(final Callback<String> callback) {
        int user = ParityApplication.getInstance().getUserId();
        if (user == 0) {
            return;
        }

        Callback<StringModel> callback1 = new Callback<StringModel>() {
            @Override
            public void onResult(StringModel result) {
                if (callback == null) {
                    return;
                }
                if (result == null) {
                    callback.onException("-1", "stringmodel is null");
                    return;
                }
                ParityApplication.getInstance().setPhone(result.string);
                callback.onResult(result.string);
            }

            @Override
            public void onException(String code, String reason) {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        };
        DataSourceRemote.getInstance().requestPhone(user, callback1);
    }

    public void requestName(final Callback<String> callback) {
        int user = ParityApplication.getInstance().getUserId();
        if (user == 0) {
            return;
        }
        Callback<StringModel> callback1 = new Callback<StringModel>() {
            @Override
            public void onResult(StringModel result) {
                if (callback == null) {
                    return;
                }
                if (result == null) {
                    callback.onException("-1", "stringmodel is null");
                    return;
                }
                ParityApplication.getInstance().setAccountName(result.string);
                callback.onResult(result.string);
            }

            @Override
            public void onException(String code, String reason) {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        };
        DataSourceRemote.getInstance().requestName(user, callback1);
    }

    public void getComments(List<String> ids, String index, final Callback<CommentReturnObject> callback) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        if (index == null || "".equals(index)) {
            index = "1";
        }
        StringBuilder id = new StringBuilder();
        for (String s : ids) {
            id.append(s).append(",");
        }
        id.deleteCharAt(id.length()-1);

        Callback<CommentReturnModel> modelCallback = new Callback<CommentReturnModel>() {
            @Override
            public void onResult(CommentReturnModel commentReturnModel) {
                if (commentReturnModel != null && callback != null) {
                    callback.onResult(commentReturnModel.toObject());
                } else {
                    Log.e("[DataCenter]searchGoods", " modelList is null");
                }
            }

            @Override
            public void onException(String code, String reason) {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        };

        DataSourceRemote.getInstance().getComments(id.toString(), index, modelCallback);
    }

    public void getAttributes(List<String> ids, final Callback<List<AttributeObject>> callback) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        StringBuilder id = new StringBuilder();
        for (String s : ids) {
            id.append(s).append(",");
        }
        id.deleteCharAt(id.length()-1);

        Callback<List<AttributeModel>> modelCallback = new Callback<List<AttributeModel>>() {
            @Override
            public void onResult(List<AttributeModel> attributeModels) {
                if (attributeModels != null && callback != null) {
                    callback.onResult(AttributeObject.fromModels(attributeModels));
                } else {
                    Log.e("[DataCenter]", "getAttributes modelList is null");
                }
            }

            @Override
            public void onException(String code, String reason) {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        };

        DataSourceRemote.getInstance().getAttributes(id.toString(), modelCallback);
    }

    public void favorite(String id, String keyword, String sort, boolean cancel, final Callback<String> callback) {
        int user = ParityApplication.getInstance().getUserId();
        if (user == 0) {
            return;
        }
        if (id == null || TextUtils.isEmpty(id)
                || keyword == null || TextUtils.isEmpty(keyword)
                || sort == null || TextUtils.isEmpty(sort)) {
            return;
        }
        Callback<StringModel> callback1 = new Callback<StringModel>() {
            @Override
            public void onResult(StringModel result) {
                if (callback == null) {
                    return;
                }
                if (result == null) {
                    callback.onException("favorite", "stringmodel is null");
                    return;
                }
                callback.onResult(result.string);
            }

            @Override
            public void onException(String code, String reason) {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        };
        DataSourceRemote.getInstance().favorite(user, id, keyword, sort, cancel, callback1);
    }

    public void getFavorites(final Callback<List<ParityObject>> callback) {
        int user = ParityApplication.getInstance().getUserId();
        if (user == 0) {
            return;
        }

        Callback<List<ParityModel>> modelCallback = new Callback<List<ParityModel>>() {
            @Override
            public void onResult(List<ParityModel> result) {
                if (result != null && callback != null) {
                    callback.onResult(ParityObject.fromModels(result));
                } else {
                    Log.e("[DataCenter]", "getFavorites modelList is null");
                }
            }

            @Override
            public void onException(String code, String reason) {
                if (callback != null) {
                    callback.onException(code, reason);
                }
            }
        };

        DataSourceRemote.getInstance().getFavorites(user, modelCallback);
    }
}
