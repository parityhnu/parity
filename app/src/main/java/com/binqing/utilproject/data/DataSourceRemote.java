package com.binqing.utilproject.data;

import android.text.TextUtils;

import com.binqing.utilproject.Callback;
import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.model.SearchModel;
import com.binqing.utilproject.data.model.UserModel;
import com.binqing.utilproject.data.object.SearchObject;
import com.binqing.utilproject.http.HttpUtil;
import com.google.gson.internal.LinkedTreeMap;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Response;

public class DataSourceRemote {

    private static volatile DataSourceRemote mInstance;

    public static DataSourceRemote getInstance() {
        if (mInstance == null) {
            synchronized (DataSourceRemote.class) {
                if (mInstance == null) {
                    mInstance = new DataSourceRemote();
                }
            }
        }
        return mInstance;
    }

    public void searchGoods(SearchObject searchObject, final Callback<SearchModel> callback) {
        if (searchObject == null) {
            return;
        }
        String path = "search";
        Map<String, String> options = new HashMap<>();
        options.put("name", searchObject.getGoodsName());
        options.put("page", searchObject.getPage());
        retrofit2.Callback<Object> callback1 = new retrofit2.Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (callback != null) {
                    callback.onResult((SearchModel) parseObject(SearchModel.class, response));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (callback != null) {
                    callback.onException("connectionException", String.valueOf(t));
                }
            }
        };
        HttpUtil.get(path, options, callback1);
    }

    public void register(String account, String password, final Callback<UserModel> callback) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            return;
        }
        String path = "register";
        Map<String, String> options = new HashMap<>();
        options.put("account", account);
        options.put("password", password);
        retrofit2.Callback<Object> callback1 = new retrofit2.Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (callback != null) {
                    callback.onResult((UserModel) parseObject(UserModel.class, response));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (callback != null) {
                    callback.onException("connectionException", String.valueOf(t));
                }
            }
        };
        HttpUtil.post(path, options, callback1);
    }

    /**
     * 需要解析列表时使用
     * @param clazz
     * @param response
     * @return
     */
    private List<Object> parseList(Class<?> clazz, Response<List<Object>> response) {
        List<Object> objectList = response.body();
        List<Object> result = new ArrayList<>();
        for(Object o : objectList) {
            if (o == null) {
                continue;
            }
            ArrayList<LinkedTreeMap> body = (ArrayList<LinkedTreeMap>) o;
            Object object = parse(clazz, body);
            if (object != null) {
                result.add(object);
            }
        }
        return result;
    }

    /**
     * 解析实体对象时使用
     * @param clazz
     * @param response
     * @return
     */
    private Object parseObject(Class<?> clazz, Response<Object> response) {
        if (response.body() instanceof LinkedTreeMap) {
            ArrayList<LinkedTreeMap> arrayList = new ArrayList<>(1);
            arrayList.add((LinkedTreeMap) response.body());
            return parse(clazz, arrayList);
        }
        ArrayList<LinkedTreeMap> body = (ArrayList<LinkedTreeMap>) response.body();
        return parse(clazz, body);
    }

    private Object parse(Class<?> clazz, ArrayList<LinkedTreeMap> body) {
        Object result = new Object();
        if (body == null) {
            return null;
        }
        for(LinkedTreeMap linkedTreeMap : body) {
            if (linkedTreeMap == null) {
                continue;
            }
            try {
                Object object = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                List<Field> fieldList = getOrderedField(fields);
                Set<Map.Entry> set = linkedTreeMap.entrySet();
                int index = 0;
                for(Map.Entry entry : set) {
                    if (entry == null) {
                        continue;
                    }
                    Field field = fieldList.get(index);
                    if (field.getType() == int.class || field.getType() == Integer.class) {
                        DecimalFormat df = new DecimalFormat("######0");
                        int x = Integer.parseInt(df.format(entry.getValue()));
                        field.set(object, x);
                    } else {
                        field.set(object, entry.getValue());
                    }
                    index ++;
                }
                result = object;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private  List<Field> getOrderedField(Field[] fields) {
        List<Field> fieldList = new ArrayList<>();
        for (Field f : fields) {
            if(f.getAnnotation(Member.class) != null) {
                int index = 0;
                for (Field field : fieldList) {
                    if (field.getAnnotation(Member.class).order() < f.getAnnotation(Member.class).order()) {
                        index++;
                        continue;
                    }
                    break;
                }
                fieldList.add(index, f);
            }
        }
        return fieldList;
    }

}
