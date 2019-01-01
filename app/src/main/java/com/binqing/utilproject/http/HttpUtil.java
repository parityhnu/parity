package com.binqing.utilproject.http;


import com.binqing.utilproject.Consts.Consts;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public class HttpUtil {

    public interface GetService {
        @GET("{path}")
        Call<List<Object>> getList(@Path("path") String path, @QueryMap Map<String, String> options);

        @GET("{path}")
        Call<Object> get(@Path("path") String path, @QueryMap Map<String, String> options);
    }

    public static void get(String path, Map<String, String> options, Callback<Object> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.INTERNET_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetService service = retrofit.create(GetService.class);

        Call<Object> repos = service.get(path, options);

        repos.enqueue(callback);
    }

    public static void getList(String path, Map<String, String> options, Callback<List<Object>> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.INTERNET_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetService service = retrofit.create(GetService.class);

        Call<List<Object>> repos = service.getList(path, options);

        repos.enqueue(callback);
    }

    public static void post() {

    }


}