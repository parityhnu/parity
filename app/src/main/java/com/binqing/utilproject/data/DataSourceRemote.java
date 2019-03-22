package com.binqing.utilproject.data;

import android.text.TextUtils;

import com.binqing.utilproject.Callback;
import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.model.GoodsListModel;
import com.binqing.utilproject.data.model.JDModel;
import com.binqing.utilproject.data.model.TBModel;
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

    public void searchGoods(SearchObject searchObject, final Callback<GoodsListModel> callback) {
        if (searchObject == null) {
            return;
        }
        final String path = "ip/search";
        Map<String, String> options = new HashMap<>();
        options.put("name", searchObject.getGoodsName());
        options.put("page", searchObject.getPage());
        options.put("sort", searchObject.getSort());
        retrofit2.Callback<Object> callback1 = new retrofit2.Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (callback != null) {
                    GoodsListModel goodsListModel = new GoodsListModel();
                    LinkedTreeMap<Integer, LinkedTreeMap> body = (LinkedTreeMap<Integer, LinkedTreeMap>) response.body();
                    if (body == null) {
                        callback.onResult(null);
                        return;
                    }
                    Set<Map.Entry<Integer, LinkedTreeMap>> set = body.entrySet();
                    for (Map.Entry entry : set) {
                        ArrayList<LinkedTreeMap> arrayList = new ArrayList<>();
                        if (entry.getValue() instanceof ArrayList) {
                            arrayList = (ArrayList<LinkedTreeMap>) entry.getValue();
                        } else if (entry.getValue() instanceof LinkedTreeMap) {
                            arrayList.add((LinkedTreeMap) entry.getValue());
                        }

                        String keyword = (String) entry.getKey();
                        if ("jdModelList".equals(keyword)) {
                            List<JDModel> jdModelList = new ArrayList<>();
                            List<Object> objectList = parse(JDModel.class, arrayList);
                            for (Object o : objectList) {
                                if (o != null) {
                                    JDModel model = (JDModel) o;
                                    jdModelList.add(model);
                                }
                            }
                            goodsListModel.jdModelList = jdModelList;
                        } else if ("tbModelList".equals(keyword)) {
                            List<TBModel> tbModelList = new ArrayList<>();
                            List<Object> objectList = parse(TBModel.class, arrayList);
                            for (Object o : objectList) {
                                if (o != null) {
                                    TBModel model = (TBModel) o;
                                    tbModelList.add(model);
                                }
                            }
                            goodsListModel.tbModelList = tbModelList;
                        } else if ("parityJdModel".equals(keyword)) {
                            List<JDModel> jdModelList = new ArrayList<>();
                            List<Object> objectList = parse(JDModel.class, arrayList);
                            for (Object o : objectList) {
                                if (o != null) {
                                    JDModel model = (JDModel) o;
                                    jdModelList.add(model);
                                }
                            }
                            if (jdModelList.isEmpty()) {
                                continue;
                            }
                            goodsListModel.parityJdModel = jdModelList.get(0);
                        } else if ("parityTbModel".equals(keyword)) {
                            List<TBModel> tbModelList = new ArrayList<>();
                            List<Object> objectList = parse(TBModel.class, arrayList);
                            for (Object o : objectList) {
                                if (o != null) {
                                    TBModel model = (TBModel) o;
                                    tbModelList.add(model);
                                }
                            }
                            if (tbModelList.isEmpty()) {
                                continue;
                            }
                            goodsListModel.parityTbModel = tbModelList.get(0);
                        }
                    }
                    callback.onResult(goodsListModel);
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

    public void register(String account, String password, String phone, final Callback<UserModel> callback) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone)) {
            return;
        }
        String path = "user/register";
        Map<String, String> options = new HashMap<>();
        options.put("account", account);
        options.put("password", password);
        options.put("phone", phone);
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

    public void login(String account, String password, final Callback<UserModel> callback) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            return;
        }
        String path = "user/login";
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

    public void requestPhone(int user, final Callback<String> callback) {
        if (user == 0) {
            return;
        }
        String path = "user/requestPhone";
        Map<String, String> options = new HashMap<>();
        options.put("user", String.valueOf(user));
        retrofit2.Callback<Object> callback1 = new retrofit2.Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (callback != null) {
                    callback.onResult((String) parseObject(String.class, response));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (callback != null) {
                    callback.onException("-1", String.valueOf(t));
                }
            }
        };
        HttpUtil.post(path, options, callback1);
    }

    public void modify(int user, String s1, String s2, String modifyType, final Callback<String> callback) {
        if (modifyType == null || "".equals(modifyType)) {
            return;
        }
        if (user == 0) {
            return;
        }
        String path = "user/modify";
        Map<String, String> options = new HashMap<>();
        options.put("user", String.valueOf(user));
        options.put("s1", s1);
        options.put("s2", s2);
        options.put("modifyType", modifyType);
        retrofit2.Callback<Object> callback1 = new retrofit2.Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (callback != null) {
                    callback.onResult((String) parseObject(String.class, response));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (callback != null) {
                    callback.onException("-1", String.valueOf(t));
                }
            }
        };
        HttpUtil.post(path, options, callback1);
    }

    public void forgetPassword(String account, String phone, String password, final Callback<String> callback) {
        if (phone == null || "".equals(phone)) {
            return;
        }
        if (password == null || "".equals(password)) {
            return;
        }
        if (account == null || "".equals(account)) {
            return;
        }
        String path = "user/forgetPassword";
        Map<String, String> options = new HashMap<>();
        options.put("account", account);
        options.put("phone", phone);
        options.put("password", password);
        retrofit2.Callback<Object> callback1 = new retrofit2.Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (callback != null) {
                    callback.onResult((String) parseObject(String.class, response));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (callback != null) {
                    callback.onException("-1", String.valueOf(t));
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
        if (objectList == null) {
            return null;
        }
        ArrayList<LinkedTreeMap> mapArrayList = new ArrayList<>();
        for (Object o : objectList) {
            if (o != null) {
                mapArrayList.add((LinkedTreeMap) o);
            }
        }
        return parse(clazz, mapArrayList);
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
            List<Object> o = parse(clazz, arrayList);
            if (o != null && !o.isEmpty()) {
                return o.get(0);
            }
            return null;
        }
        ArrayList<LinkedTreeMap> body = (ArrayList<LinkedTreeMap>) response.body();
        List<Object> o = parse(clazz, body);
        if (o != null && !o.isEmpty()) {
            return o.get(0);
        }
        return null;
    }

    /**
     * 只能处理一层结构，如有嵌套需要在传进来之前进行拆分
     * @param clazz
     * @param body
     * @return
     */
    private List<Object> parse(Class<?> clazz, ArrayList<LinkedTreeMap> body) {
        List<Object> result = new ArrayList<>();
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
                int length = fieldList.size();
                Set<Map.Entry> set = linkedTreeMap.entrySet();
                int index = 0;
                for(Map.Entry entry : set) {
                    if (entry == null) {
                        continue;
                    }
                    if (index >= length) {
                        break;
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
                result.add(object);
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
