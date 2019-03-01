package com.binqing.utilproject.http;


import com.binqing.utilproject.Consts.Consts;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public class HttpUtil {

    public interface GetService {
        @GET("{path}")
        Call<List<Object>> getList(@Path(value = "path", encoded = true) String path, @QueryMap Map<String, String> options);

        @GET("{path}")
        Call<Object> get(@Path(value = "path", encoded = true) String path, @QueryMap Map<String, String> options);
    }

    public interface PostService {
        @POST("{path}")
        @FormUrlEncoded
        Call<List<Object>> postList(@Path(value = "path", encoded = true) String path, @FieldMap Map<String, String> options);

        @POST("{path}")
        @FormUrlEncoded
        Call<Object> post(@Path(value = "path", encoded = true) String path, @FieldMap Map<String, String> options);
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

    public static void post(String path, Map<String, String> options, Callback<Object> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.INTERNET_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostService service = retrofit.create(PostService.class);

        Call<Object> repos = service.post(path, options);

        repos.enqueue(callback);
    }


}
